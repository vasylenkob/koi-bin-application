package com.vasylenkob.pastebin.models;

import lombok.Data;

@Data
public class PostForm {
 private String postTitle;
 private String content;
 private Integer lifetimeMinutes;
}
