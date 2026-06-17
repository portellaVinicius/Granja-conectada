package com.portella.granjaconectada.Service;

import com.portella.granjaconectada.Dto.DtoRequest.UserRequestDto;
import com.portella.granjaconectada.Exceptions.BussinesExcepiton;
import com.portella.granjaconectada.Exceptions.ExceptionNotFound;
import com.portella.granjaconectada.mapper.UserMapper;
import com.portella.granjaconectada.Model.UserModel;
import com.portella.granjaconectada.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserModel salvar(UserRequestDto dto){
        validarEmailDuplicado(dto.email());
        return userRepository.save(userMapper.toModel(dto));
    }

    @Transactional(readOnly = true)
    public UserModel buscarUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ExceptionNotFound("Usuário não encontrado"));
    }

    //TODO: melhorar esse metodo quando usar o spring security
    public UserModel trocarSenha(Long userId, UserRequestDto dto){
        UserModel userModel = buscarUser(userId);

        if (!StringUtils.hasText(dto.senha())) {
            throw new BussinesExcepiton("Senha inválida");
        }

        userModel.setSenha(dto.senha());
        return userRepository.save(userModel);
    }

    private void validarEmailDuplicado(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BussinesExcepiton("Email já cadastrado");
        }
    }
}
