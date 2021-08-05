package com.xl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 日期转换器
 */
@Component
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}

}
