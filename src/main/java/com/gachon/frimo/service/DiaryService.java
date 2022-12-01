package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class DiaryService {
    @Autowired
    private static DiaryRepository diaryRepository;
    @Autowired
    private static UserRepository userRepository;


    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiaries(Long userPk){
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream().map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByMonth(Long userPk, int month){
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
        .filter(diary -> (diary.getDateCreatedMonth() == month))
        .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());

    }
    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByYear(Long userPk, int year){
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
        .filter(diary -> (diary.getDateCreatedYear() == year))
        .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }

    //일기 하나
    @Transactional
    public DiaryDto.GetDiaryResponseDto getOneDiary(Long userPk, Long diaryPk){
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        return DiaryDto.toGetDiaryResponseDto(diary);
    }

    //일기추가
    @Transactional
    public void addDiary (DiaryDto.AddDiaryRequestDto addDiaryRequestDto){
        String diaryTitle = addDiaryRequestDto.getDiaryTitle();
        String diaryContent = addDiaryRequestDto.getDiaryContent();
        Long userPk = addDiaryRequestDto.getUserPk(); // userPk로 service단에서 user찾기
        LocalDateTime dateCreated = addDiaryRequestDto.getDateCreated();
        int dateCreatedYear = dateCreated.getYear();
        int dateCreatedMonth = dateCreated.getMonthValue();
        String mainSent="";
        String imagePath ="";
        // TODO : tag에서 mainSent 계산하는 함수 필요

        User user = userRepository.findByUserPk(userPk);
        Diary newDiary = Diary.builder()
                        .diaryContent(diaryContent)
                        .diaryTitle(diaryTitle)
                        .dateCreated(dateCreated)
                        .author(user)
                        .dateCreatedMonth(dateCreatedMonth)
                        .dateCreatedYear(dateCreatedYear)
                        .mainSent(mainSent)
                        .imagePath(imagePath)
                        .build();
        diaryRepository.save(newDiary);
    }
}
