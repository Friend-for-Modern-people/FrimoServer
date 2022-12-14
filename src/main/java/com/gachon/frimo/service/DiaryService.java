package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.DiaryDto.GetDiaryResponseDto;

import lombok.RequiredArgsConstructor;

import com.gachon.frimo.domain.user.User;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiaries(Long userPk) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)

                .collect(Collectors.toList());
    }

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByYear(Long userPk, int year) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getDateCreatedYear() == year))
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByMonth(Long userPk, int year, int month) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getDateCreatedYear() == year && diary.getDateCreatedMonth() == month))
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }

    // ?????? ??????
    @Transactional
    public DiaryDto.GetDiaryResponseDto getOneDiary(Long diaryPk) {
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        return DiaryDto.toGetDiaryResponseDto(diary);
    }

    // ????????????
    // TODO : tag??? ?????? ???????????? main_Sent??? ?????? ????????? ????????? ???????????? ?????? X
    // ?????? ???????????? ????????? diaryPK??? ????????? tag??? ???????????? ??????.
    // ????????? ?????? patch??? ??????????????? ??????.
    // patch??? ????????? ?????????????

    // ?????? ????????? ????????? ????????? ?????? ??? ????????? pk?????? ???????????? tag???????????? mainSent??? ???????????? ???????????? ??????????????? ?????????
    // (PostMapping?????????)

    // ???????????? ????????? ?????? ??? diary ??????, tag??????, diary patch

    @Transactional
    public void addDiary(DiaryDto.AddDiaryRequestDto addDiaryRequestDto) {
        String diaryTitle = addDiaryRequestDto.getDiaryTitle();
        String diaryContent = addDiaryRequestDto.getDiaryContent();
        Long userPk = addDiaryRequestDto.getUserPk(); // userPk??? service????????? user??????
        LocalDateTime dateCreated = addDiaryRequestDto.getDateCreated();
        int dateCreatedYear = dateCreated.getYear();
        int dateCreatedMonth = dateCreated.getMonthValue();
        int mainSent = 7; // initial value
        String imagePath = "";
        // TODO : tag?????? mainSent ???????????? ?????? ??????
        System.out.print("userid : " + userPk);
        User user = userRepository.findByUserPk(userPk);
        // System.out.print("userid : "+user.getUserId());
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

    @Transactional
    public int getDiariesCnt(Long userPk) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.size();
    }

    @Transactional
    public List<GetDiaryResponseDto> getDiariesBySent(Long userPk, int sent) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getMainSent() == sent))
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }

}
