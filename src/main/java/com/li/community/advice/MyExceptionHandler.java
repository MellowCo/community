package com.li.community.advice;

import com.li.community.exception.CustomizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-02-28 15:17
 */

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    public ModelAndView MyException(CustomizeException e) {
        return new ModelAndView("/error/error", "message", e.getMessage());
    }
}
