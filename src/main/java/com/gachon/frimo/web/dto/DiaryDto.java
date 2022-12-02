package com.gachon.frimo.web.dto;

import java.time.LocalDateTime;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetDiaryResponseDto { 
        //일기(최신순)으로 가져오기
        private Long diaryPk;
        private String diaryTitle;
        private String diaryContent;
        private User user; // userPk
        private LocalDateTime dateCreated;
        private int dateCreatedYear;
        private int dateCreatedMonth;
        // 제일 빈도 수 많은 감정을 뽑기 -> 별도의 함수 만들기
        private int mainSent;

        @Builder
        public GetDiaryResponseDto(Long diaryPk, String diaryTitle, String diaryContent, User user, LocalDateTime dateCreated, int dateCreatedYear,int dateCreatedMonth, int mainSent){
            this.diaryPk=diaryPk;
            this.diaryTitle = diaryTitle;
            this.diaryContent = diaryContent;
            this.user = user;
            this.dateCreated = dateCreated;
            this.dateCreatedMonth= dateCreatedMonth;
            this.dateCreatedYear = dateCreatedYear;
            this.mainSent= mainSent;
        }
        
    }
    //Entity -> DTO 
    public static GetDiaryResponseDto toGetDiaryResponseDto(Diary diary){
        Long diaryPk=diary.getDiaryPk();
        String diaryTitle = diary.getDiaryTitle();
        String diaryContent = diary.getDiaryContent();
        User user = diary.getAuthor();
        LocalDateTime dateCreated = diary.getDateCreated();
        int dateCreatedMonth= diary.getDateCreatedMonth();
        int dateCreatedYear = diary.getDateCreatedYear();
        int mainSent = diary.getMainSent();
        
        GetDiaryResponseDto getDiaryResponseDto = GetDiaryResponseDto.builder()
                            .diaryPk(diaryPk)
                            .diaryTitle(diaryTitle)
                            .diaryContent(diaryContent)
                            .user(user)
                            .dateCreated(dateCreated)
                            .dateCreatedMonth(dateCreatedMonth)
                            .dateCreatedYear(dateCreatedYear)
                            .mainSent(mainSent)
                            .build();
        return getDiaryResponseDto;
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddDiaryRequestDto {
        
        private String diaryTitle;
        private String diaryContent;
        private Long userPk; // userPk로 service단에서 user찾기
        private LocalDateTime dateCreated;

        @Builder
        public AddDiaryRequestDto(String diaryTitle, String diaryContent, Long userPk, LocalDateTime dateCreated ) {
            this.diaryTitle = diaryTitle;
            this.diaryContent = diaryContent;
            this.userPk = userPk;
            this.dateCreated = dateCreated;
        }
        
    }
}
