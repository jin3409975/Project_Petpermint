package com.ssafy.api.service;

public interface EmailValidateService {
    boolean validate(String email, String authNum);
    void makeAuthNum();
    String emailContent(String email);
    void sendEmail(String setFrom, String toMail, String title, String content);
}
