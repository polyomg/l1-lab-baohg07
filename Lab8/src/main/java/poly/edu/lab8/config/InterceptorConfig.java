package poly.edu.lab8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Auth interceptor protects specific patterns
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");

        // Log interceptor logs accesses that require login
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/admin/**", "/account/**", "/order/**");
    }
}
