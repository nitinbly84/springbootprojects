package com.nitinagrawal.security;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
 
    private int successCount = 0;
    private int failCount = 0;
     
    public LoginServiceImpl() {
    }
     
    public boolean login(String userName, char[] password) {
        boolean success;
        if (userName.equals("admin") && "password".toCharArray().equals(password)) {
        	successCount++;
            success = true;
        }
        else {
        	failCount++;
            success = false;
        }
        System.out.println(failCount);
        System.out.println(successCount);
        return success;
    }
}