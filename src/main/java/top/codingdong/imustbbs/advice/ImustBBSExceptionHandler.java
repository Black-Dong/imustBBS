package top.codingdong.imustbbs.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

        if (e instanceof ImustBBSException){
            System.err.println(e.getMessage());
            model.addAttribute("message", e.getMessage());
        }else {
            HttpStatus status = getStatus(request);
            if (status.is4xxClientError()){
                model.addAttribute("message","请求错误，请确认后重试。");
            }else if (status.is5xxServerError()){
                model.addAttribute("message", "服务器错误，请稍后重试！！！");
            }else {
                model.addAttribute("message", "未知错误！！！");
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
