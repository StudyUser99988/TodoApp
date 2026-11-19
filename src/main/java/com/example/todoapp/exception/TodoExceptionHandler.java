package com.example.todoapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class TodoExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex) {
        return "error/403";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn(
                "Method Not Allowed. method={}",
                ex.getMethod());
        return "error/405";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("URLが存在しません", ex);
        return "error/404";
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public String handleTodoNotFound(
            TodoNotFoundException ex) {

        log.warn("TODOが存在しません", ex);

        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception ex) {

        log.error("システムエラー", ex);

        return "error/500";
    }
}
