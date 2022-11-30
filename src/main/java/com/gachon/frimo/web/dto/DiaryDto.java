package com.gachon.frimo.web.dto;

import java.time.LocalDateTime;

import com.gachon.frimo.domain.diary.Diary;

import lombok.Builder;

public class DiaryDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetDiariesResponseDto { 
        //일기(최신순)으로 가져오기
        private Long diaryPk;
        private String diaryTitle;
        private String diaryContent;
        private Long userPk; // userPk
        private LocalDateTime dateCreated;
        private int dateCreatedYear;
        private int dateCreatedMonth;
        // 제일 빈도 수 많은 감정을 뽑기 -> 별도의 함수 만들기
        private String mainSent;

        @Builder
        public GetDiariesResponseDto(Diary diary){
            this.diaryPk=diary.getDiaryPk();
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.userPk = diary.getAuthor().getUserPk();
            this.dateCreated = diary.getDateCreated();
            this.dateCreatedMonth= diary.getDateCreatedMonth();
            this.dateCreatedYear = diary.getDateCreatedYear();
        }
        

    }

    // @Getter
    // @NoArgsConstructor(access = AccessLevel.PROTECTED)
    // public static class AddCommentRequestDto {
    //     // AddCommentRequestDto 형식으로 넘겨주면 
    //     private Long diaryPk; // {diaryPk, authorPk}
    //     private String commentContent;
    //     private LocalDateTime commentDate;
    //     private Diary diary;

    //     @Builder
    //     public AddCommentRequestDto( Diary diary, String commentContent, LocalDateTime commentDate) {
    //         this.diary = diary;
    //         this.commentContent = commentContent;
    //         this.commentDate = commentDate;
    //     }
    //     public CommentAI toEntity() {
    //         return CommentAI.builder()
    //             .diary(diary)
    //             .commentDate(commentDate)
    //             .commentContent(commentContent)
    //             .build();
    //     }

    // }
}
