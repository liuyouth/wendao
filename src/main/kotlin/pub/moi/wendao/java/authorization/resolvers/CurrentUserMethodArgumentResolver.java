package pub.moi.wendao.java.authorization.resolvers;


import pub.moi.wendao.java.authorization.annotation.CurrentUser;
import pub.moi.wendao.java.config.Constants;
import pub.moi.wendao.db.UserRepository;
import pub.moi.wendao.model.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @see CurrentUser
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        User user = (User) webRequest.getAttribute(Constants.CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            User mUser= userRepository.findByNumber(currentUserId);

            //从数据库中查询并返回  不会在此写入



//            Lock.lock(currentUserId);
            if (mUser == null) {
//                user.setId(0L);
//                mUser = userRepository.save(user);
                throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
            } else {
                mUser.setToken(user.getToken());
                System.out.println("鉴权Number:"+currentUserId + "本库主键 " + mUser.getId() + "Acc： "+user.getId());
                mUser = userRepository.save(mUser);
            }

//            Lock.unlock(currentUserId);
            return mUser;

        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
