package com.portella.granjaconectada.mapper;

import com.portella.granjaconectada.Dto.DtoRequest.UserRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.UserResponseDto;
import com.portella.granjaconectada.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toModel(UserRequestDto userRequestDto){
        if (userRequestDto == null) return null;
        UserModel userModel = new UserModel();

        userModel.setEmail(userRequestDto.email());
        userModel.setSenha(userRequestDto.senha());
        return userModel;
    }

    public UserResponseDto fromModel(UserModel userModel){
        if (userModel == null) return null;

        return new UserResponseDto(
                userModel.getId(),
                userModel.getEmail());
    }
}
