package com.shl.demo.design;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String username;
    private String city;
    private List<String> buyProducts;
    private boolean isNewUser;
}
