package com.ingesup.java.qcm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ingesup.java.qcm.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String VIEW_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_SUFFIX = ".jsp";

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver jstlViewResolver = new UrlBasedViewResolver();
		jstlViewResolver.setViewClass(JstlView.class);
		jstlViewResolver.setPrefix(VIEW_PREFIX);
		jstlViewResolver.setSuffix(VIEW_SUFFIX);

		return jstlViewResolver;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");

		return localeChangeInterceptor;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(viewResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/", "/");
	}
}
