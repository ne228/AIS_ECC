package com.example.ais_ecc.tagHanlers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ErrorTagHandler extends SimpleTagSupport {
    private String key;
    private Map<String, String> errorMap;

    public void setKey(String key) {
        this.key = key;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String value = errorMap.get(key);
        if (value != null) {
            out.println("<p class='" + key + "'>" + value + "</p>");
        }
    }
}
