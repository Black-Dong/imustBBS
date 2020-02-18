package top.codingdong.imustbbs.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.codingdong.imustbbs.exception.ImustBBSException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/18 12:25
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        logger.error("Request URL: {}, Exception: {}", request.getRequestURL(), e);


        if (e instanceof ImustBBSException) {
            ImustBBSException imustBBSException = (ImustBBSException) e;
            ModelAndView mv = new ModelAndView();
            mv.addObject("url", request.getRequestURL());
            mv.addObject("code", imustBBSException.getCode());
            mv.addObject("message", imustBBSException.getMessage());
            mv.setViewName("error/error");
            return mv;
        } else {
            throw e;
        }
    }
}
