package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

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
    public List<CommentAIDto.GetCommentResponseDto> getCommentInDiary(Long diaryPk) { 
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<CommentAI> comments = commentAIRepository.findAllByDiary(diary);

        return comments.stream().map(CommentAIDto::toGetCommentResponseDto)
                .collect(Collectors.toList());
    }
}
