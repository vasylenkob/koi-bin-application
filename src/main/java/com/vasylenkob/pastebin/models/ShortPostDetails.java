package com.vasylenkob.pastebin.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortPostDetails {
    private String hash;
    private String title;
    private LocalDateTime expirationDate;
}
