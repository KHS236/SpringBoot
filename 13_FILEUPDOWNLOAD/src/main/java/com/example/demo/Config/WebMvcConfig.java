package com.example.demo.Config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc //app프로퍼티에서 했던 멀티파트 컨피그 설정/MVC설정을 여기서 하겠따
public class WebMvcConfig implements WebMvcConfigurer /*기본 설정을 담은 클래스*/
    {
//기본경로 : /resources/static (http://localhost:5555/common.css를 확인 가능)
//#EnableWebMvd == static까지가 기본경로인데 저 기본 경로를 바꿀 수 있게 해주는 어노테이션

        //MULTIPART CONFIG
        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            factory.setMaxFileSize(DataSize.ofMegabytes(20));       // 파일 1개 최대
            factory.setMaxRequestSize(DataSize.ofMegabytes(20));    // 요청 전체 최대
            factory.setFileSizeThreshold(DataSize.ofMegabytes(20)); // 메모리 임계치(유사: maxInMemorySize)
            return factory.createMultipartConfig();
        }

//ViewResolver 설정(JSP 용) jsp경로 설정을 여기다 할 수 있음(참고)
//        @Override
//        public void configureViewResolvers(ViewResolverRegistry registry) {
//            registry.jsp("/WEB-INF/views",".jsp");
//        }

        //JSP ViewResolver(직접 객체 생성)
//    @Bean
//    public ViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver
//                =new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/views");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

        //ThymeLeaf ViewResolver
        // Thymeleaf 옵션 설정

        @Bean
        public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setPrefix("classpath:/templates/"); // Thymeleaf 템플릿 위치
            resolver.setSuffix(".html");                 // 확장자
            resolver.setCharacterEncoding("UTF-8");
            resolver.setCacheable(false); // 개발 중에는 캐시 끄기
            return resolver;
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine engine = new SpringTemplateEngine();
            engine.setTemplateResolver(templateResolver());
            engine.setEnableSpringELCompiler(true);
            return engine;
        }

        @Bean
        public ThymeleafViewResolver thymeleafViewResolver() {
            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
            viewResolver.setTemplateEngine(templateEngine());
            viewResolver.setCharacterEncoding("UTF-8");
            viewResolver.setOrder(1);
            return viewResolver;
        }



        //정적자원 관리 (classpath:/static이 기본 경로임)
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/");
            registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/css/");
            registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/js/");
            //기본 경로 /resources/common.css(아무꺼나) / 내부에서 찾는 매칭 경로/resources/
        }


    }
