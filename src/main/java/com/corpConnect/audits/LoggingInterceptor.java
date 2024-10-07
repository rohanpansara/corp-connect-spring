package com.corpConnect.audits;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.corpConnect.utils.constants.InterceptorColors.BLUE;
import static com.corpConnect.utils.constants.InterceptorColors.GREEN;
import static com.corpConnect.utils.constants.InterceptorColors.MAGENTA;
import static com.corpConnect.utils.constants.InterceptorColors.RED;
import static com.corpConnect.utils.constants.InterceptorColors.RESET;
import static com.corpConnect.utils.constants.InterceptorColors.YELLOW;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Value("${colored-logs}")
    private static boolean USE_COLOR_LOGGING;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            String controllerName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();
            String httpMethod = request.getMethod();
            String requestURI = request.getRequestURI();

            // Construct a well-formatted log message without colors
            String logMessage = String.format(
                    "[%s] Controller: %s | Method: %s | HTTP Method: %s | URL: %s",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), // Timestamp
                    controllerName,
                    methodName,
                    httpMethod,
                    requestURI
            );

            // Apply colors if toggle is enabled
            if (USE_COLOR_LOGGING) {
                logMessage = String.format(
                        "%s[%s] %sController:%s %s %s| Method:%s %s %s| HTTP Method:%s %s %s| URL:%s %s%s",
                        RED, // Reset color
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), // Timestamp
                        RESET,
                        BLUE, // Color for Controller
                        controllerName,
                        RESET,
                        YELLOW, // Color for Method
                        methodName,
                        RESET,
                        GREEN, // Color for HTTP Method
                        httpMethod,
                        RESET,
                        MAGENTA, // Color for URL
                        requestURI,
                        RESET // Reset color at the end
                );
            }

            logger.info(logMessage);
        }
        return true;
    }
}

