package com.VacationProject.VacationProjectFrontEnd;

import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeDetailsMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        EmployeeDetailsMapper employeeDetailsMapper = (EmployeeDetailsMapper) authentication.getPrincipal();
        String redirectURL;
        String role = employeeDetailsMapper.getAuthorities()
                .stream().findFirst().get().toString();

        switch (role) {
            case "ROLE_EMPLOYEE" -> redirectURL = "/employee/home";
            case "ROLE_ADMIN" -> redirectURL = "/admin/dashboard";
            default -> redirectURL = "/vacations/login";
        }
        response.sendRedirect(redirectURL);
    }
}
