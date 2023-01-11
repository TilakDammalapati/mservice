package com.byms.mservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private String username;
    private UUID id;
}
