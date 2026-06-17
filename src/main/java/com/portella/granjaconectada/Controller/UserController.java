package com.portella.granjaconectada.Controller;

import com.portella.granjaconectada.Dto.DtoRequest.UserRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.UserResponseDto;
import com.portella.granjaconectada.mapper.UserMapper;
import com.portella.granjaconectada.Model.UserModel;
import com.portella.granjaconectada.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> salvar(@RequestBody @Valid UserRequestDto dto){
        UserModel userModel = userService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.fromModel(userModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> listar(@PathVariable Long id){
        UserModel userModel = userService.buscarUser(id);
        return ResponseEntity.ok(userMapper.fromModel(userModel));
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<UserResponseDto> trocarSenha(@PathVariable Long id,
                                                       @RequestBody @Valid UserRequestDto dto){
        UserModel userModel = userService.trocarSenha(id, dto);
        return ResponseEntity.ok(userMapper.fromModel(userModel));
    }
}
