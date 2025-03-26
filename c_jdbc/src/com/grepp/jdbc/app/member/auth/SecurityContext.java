package com.grepp.jdbc.app.member.auth;

import java.security.Security;

public class SecurityContext {

    private Principal principal;
    private static SecurityContext instance;

    public static SecurityContext getInstance(){
        if (instance == null){
            instance = new SecurityContext();
        }

        return instance;
    }

    public SecurityContext() {
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }
}
