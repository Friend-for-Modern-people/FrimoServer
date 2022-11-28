package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.gachon.frimo.domain.user.User;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.UserDto;

public class UserService {
    @Autowired
    private static UserRepository userRepository;

    @Transactional
    public void UserRegist(UserDto.RegistRequestDto registRequestDto){
        userRepository.save(registRequestDto.toEntity());
    }

    @Transactional
    public boolean checkUserNNDuplicate(String nickname){
        return userRepository.existsByUserNN(nickname);
    }

    @Transactional
    public Long deleteUser (Long userPk){
        userRepository.deleteByUserPk(userPk);
        return userPk;
    }

    @Transactional 
    public UserDto.GetUserOnlyInfoResponseDto getUserInfoResponseDto(Long userPk){
        User user =userRepository.findByUserPk(userPk);
        return UserDto.GetUserOnlyInfoResponseDto.builder()
                                            .userId(user.getUserId())
                                            .userNN(user.getUserNN())
                                            .userPk(user.getUserPk())
                                            .build();
                                            
    }
    
}
