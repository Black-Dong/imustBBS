package top.codingdong.imustbbs.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.codingdong.imustbbs.enums.ImustBBSErrorEnum;
import top.codingdong.imustbbs.exception.ImustBBSException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/11 19:30
 */
@ControllerAdvice
public class ImustBBSExceptionHandler {

    @ExceptionHandler(Exception.class)
    String handleControllerException(HttpServletRequest request, Throwable e, Model model) {

        if (e instanceof ImustBBSException) {
            model.addAttribute("message", e.getMessage());
        } else {
            HttpStatus status = getStatus(request);
            if (status.is4xxClientError()) {
                model.addAttribute("message", ImustBBSErrorEnum.ERROR_4XX.getMessage());
            } else if (status.is5xxServerError()) {
                model.addAttribute("message", ImustBBSErrorEnum.ERROR_5XX.getMessage());
            } else {
                model.addAttribute("message", ImustBBSErrorEnum.ERROR_OTHER.getMessage());
            }
        }
        return "error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
