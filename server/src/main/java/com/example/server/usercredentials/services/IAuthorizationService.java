package com.example.server.usercredentials.services;

import com.example.server.usercredentials.model.dto.AuthorizationDto;

public interface IAuthorizationService {
    void authorizeUser(AuthorizationDto authorizationDto);
}
