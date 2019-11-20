package com.ftu.gateway.controller;

import com.ftu.gateway.common.ErrorResponse;
import com.ftu.gateway.common.WebUtils;
import io.jmnarloch.spring.request.correlation.support.RequestCorrelationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ZuulCustomErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/vnd.error+json")
    public @ResponseBody ResponseEntity error(HttpServletRequest request) {
        final String logref = "IP send request: "+ WebUtils.getClientIp(request) + ", uri: "+request.getRequestURI();
        final int status = getErrorStatus(request);
        final String errorMessage = getErrorMessage(request);
        final ErrorResponse error = new ErrorResponse(new Date(), status, errorMessage, logref);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
}
