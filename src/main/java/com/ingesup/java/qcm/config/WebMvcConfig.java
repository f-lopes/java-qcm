package com.ingesup.java.qcm.config;

import com.ingesup.java.qcm.handler.GitInfoInterceptor;
import com.ingesup.java.qcm.util.GitRepositoryState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
@ComponentScan(basePackages = "com.ingesup.java.qcm.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String VIEW_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_SUFFIX = ".jsp";

	final GitRepositoryState gitRepositoryState;

	public WebMvcConfig(GitRepositoryState gitRepositoryState) {
		this.gitRepositoryState = gitRepositoryState;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver jstlViewResolver = new InternalResourceViewResolver();
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
	public GitInfoInterceptor gitInfoInterceptor() {
		return new GitInfoInterceptor(this.gitRepositoryState);
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");

		return localeChangeInterceptor;
	}

	@Bean
	public FormattingConversionService formattingConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(viewResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(gitInfoInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.resourceChain(true);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// Used to resolve @AuthenticationPrincipal annotation (@CurrentUser custom annotation)
		argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter("dd/MM/yyyy"));
	}

}
