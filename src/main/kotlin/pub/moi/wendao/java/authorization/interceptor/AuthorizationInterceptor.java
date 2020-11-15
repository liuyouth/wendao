package pub.moi.wendao.java.authorization.interceptor;

import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pub.moi.wendao.java.authorization.annotation.Authorization;
import pub.moi.wendao.java.authorization.manager.TokenManager;
import pub.moi.wendao.java.config.Constants;
import pub.moi.wendao.java.utils.FileUtil;
import pub.moi.wendao.model.base.Result;
import pub.moi.wendao.model.base.User;
import pub.moi.wendao.net.DataManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author ScienJus
 * @date 2015/7/30.
 * @see Authorization
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;
    Boolean b = true;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        b = true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        FileUtil.inLogger.info("AuthorizationInterceptor: "  +request.getRequestURL() +" Have Authorization :" +(method.getAnnotation(Authorization.class) != null));

        if (method.getAnnotation(Authorization.class) != null) {
            //从header中得到token
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            System.out.println("authorization：" + authorization);
            if (null != authorization) {
                DataManager dataManager = new DataManager();
                dataManager.checkToken(authorization).subscribe(new Consumer<Result<User>>() {
                    @Override
                    public void accept(Result<User> userResult) throws Exception {
                        //验证token
                        //        TokenModel model = manager.getToken(authorization);
                        //        if (manager.checkToken(model)) {
                        //            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                        //            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                        //            return true;
                        //        }
//                        System.out.println("eeee" + userResult.getData());
                        request.setAttribute(Constants.CURRENT_USER_ID, userResult.getData().getNumber());
                        request.setAttribute(Constants.CURRENT_USER, userResult.getData());
                        b = true;

                        //如果验证token失败，并且方法注明了Authorization，返回401错误
                        //                if (method.getAnnotation(Authorization.class) != null) {
                        //                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        //                    b = false;
                        //                }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("eeeee " + throwable);
                        if (method.getAnnotation(Authorization.class) != null) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            b = false;
                        }
                    }
                });
            }

        }
        FileUtil.inLogger.info("preHandle return "+b);
        return b;
    }

}
