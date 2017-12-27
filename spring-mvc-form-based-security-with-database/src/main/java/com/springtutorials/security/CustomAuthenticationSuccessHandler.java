package com.springtutorials.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        com.springtutorials.Model.User userDetail = (com.springtutorials.Model.User) authentication.getPrincipal();
        
        HttpSession session = request.getSession();


        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     *
     * @param authentication
     * @return
     */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isDashbordUser = false;
        boolean isSupplier = false;
        boolean isComplainManager = false;
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        OUTER:
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_USER":
                    isUser = true;
                   // set.
                    break OUTER;
                case "ROLE_ADMIN":
                    isAdmin = true;
                    break OUTER;
                case "ROLE_DASHBOARD":
                    isDashbordUser = true;
                    break OUTER;
                    
                case "ROLE_SUPPLIER":
                	isSupplier = true;
                    break OUTER;
                    
                case "ROLE_Complain":
                	isComplainManager = true;
                    break OUTER;
            }
        }

        if (isUser) {
        	//session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            return "/mydashbord";
        } else if (isAdmin) {
            return "/dashbord";
        } else if (isDashbordUser) {
            return "/dashbord";
        }else if (isSupplier) {
            return "/supplier/dashboard";
        }
        else if (isComplainManager) {
            return "/mydashbord";
        }
        
        
        else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
