package cn.dmdream.admin.config;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertConfig implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        try {
            if(s == null || s.equalsIgnoreCase("")) return null;
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
