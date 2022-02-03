package com.lgcns.icst.springthymeleaf.lec5;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

//@Component
public class ServletErrorConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage notFound = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");    // 에러 코드로 맵핑(sendError)
        ErrorPage internalServer = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");  // 에러 코드로 맵핑(sendError)
        ErrorPage sqlException = new ErrorPage(SQLException.class, "/error-page/sql");  // exception 으로 맵핑

        factory.addErrorPages(notFound, internalServer, sqlException);
    }
}
