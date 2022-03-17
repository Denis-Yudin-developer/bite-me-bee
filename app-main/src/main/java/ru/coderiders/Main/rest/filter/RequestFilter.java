package ru.coderiders.Main.rest.filter;

import ru.coderiders.Main.utils.CachedBodyHttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);

        log.info("Перехваченный запрос: метод {}, URI {}, body {}", request.getMethod(), request.getRequestURI(), new String(cachedRequest.getInputStream().readAllBytes()));
        filterChain.doFilter(cachedRequest, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
