package pub.moi.wendao.java.config;

import pub.moi.wendao.java.authorization.interceptor.AuthorizationInterceptor;
import pub.moi.wendao.java.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see CurrentUserMethodArgumentResolver
 * @see AuthorizationInterceptor
 * @author liubo
 * @date 2015/7/30.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
    /**
     * 配置的图片映射
     */
    private static final String imgPath = "file:" + Constants.UPLOAD_PATH + Constants.IMG_FILE_NAME +  "/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有访问img/virtual/**的请求映射到文件上传的路径下 C:\Users\wanghao/upload/img（图片的保存路径）
        registry.addResourceHandler("/image/**").addResourceLocations(imgPath);
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        super.addResourceHandlers(registry);
    }


}
