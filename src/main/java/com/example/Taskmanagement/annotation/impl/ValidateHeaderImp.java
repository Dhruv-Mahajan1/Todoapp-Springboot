package com.example.Taskmanagement.annotation.impl;




import com.example.Taskmanagement.annotation.ValidateHeader;
import com.example.Taskmanagement.exception.UserNotFoundException;
import com.example.Taskmanagement.models.User;
import com.example.Taskmanagement.repo.UserRepo;
import com.example.Taskmanagement.util.TokenUtil;
import com.example.Taskmanagement.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class ValidateHeaderImp {

    @Autowired
    UserRepo userRepo;


    @Before("@within(validateHeader)")
    public void validate(JoinPoint joinPoint, ValidateHeader validateHeader) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        User user = TokenUtil.validateToken(token, this.userRepo);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        } else {
            UserContext.setUser(user);
        }
    }

    @After("@within(validateHeader)")
    public void clearMDC() {
        UserContext.clear();
    }
}
