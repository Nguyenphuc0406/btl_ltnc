package com.example.demo.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalMessageUtils {
	@Autowired
	private static MessageSource messageSource;

	@Autowired
	public LocalMessageUtils(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public static String getMessage(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, null, locale);
	}
}
