package com.example.BiteMeBee.rest.filter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class BeeFamilyFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)  {

        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + getParameters(request));

        return true;
    }

    private String getParameters(HttpServletRequest request) {
        StringBuilder posted = new StringBuilder();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        if (e != null) {
            while (e.hasMoreElements()) {
                if (posted.length() > 1) {
                    posted.append("&");
                }
                String curr = (String) e.nextElement();
                posted.append(request.getParameter(curr));
            }
        }
        return posted.toString();
    }
}
