package cn.edu.bjtu.brilley.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Brilley
 * @date 2022/5/13
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 放行全部请求类型
                .allowedMethods("*")
                // .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 是否发送Cookie
                .allowCredentials(true)
                //暴露哪些头部信息
                .exposedHeaders("*");
    }
}
