package com.vasylenkob.pastebin.models;

import lombok.Data;

@Data
public class VerifyUserForm {
    private String email;
    private String verificationCode;
}
