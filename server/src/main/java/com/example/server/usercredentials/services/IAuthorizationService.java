package com.example.server.usercredentials.services;

import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.model.entity.Person;

public interface IAuthorizationService {
    Person authorizeUser(AuthorizationDto authorizationDto);
}
