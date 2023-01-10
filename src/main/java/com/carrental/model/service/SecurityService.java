package com.carrental.model.service;

public interface SecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}
