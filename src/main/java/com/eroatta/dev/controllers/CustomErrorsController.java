package com.eroatta.dev.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eroatta.dev.exceptions.BadRequestException;
import com.eroatta.dev.response.ErrorResponse;

@ControllerAdvice
@RestController
public class CustomErrorsController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public ErrorResponse handleError(HttpServletRequest request,
            HttpServletResponse response) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(
                request);

        return new ErrorResponse(response.getStatus(),
                errorAttributes.getErrorAttributes(requestAttributes, false));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequest(HttpServletRequest req,
            BadRequestException ex) {
        return new ErrorResponse(ex.getStatus(), ex.getErrorDescription());
    }
}
