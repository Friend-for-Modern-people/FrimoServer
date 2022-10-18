package com.gachon.frimo.domain.diary_interest_tag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;
import com.gachon.frimo.domain.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary_interest_tag")
public class DiaryInterestTag extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "diary") // diary_pk, author_pk
    private Diary diary;

    @Column(name = "sentiment_tag") // sent_pk (감정구분자 60개)
    private SentimentTag sentimentTag;

    @Column(name = "tag_content", length = 45) // 해시테그
    private String tagContent;

    @Column(name = "category")
    private int category;

    @Builder
    public DiaryInterestTag(String tagContent, int category, Diary diary, SentimentTag sentimentTag) {
        this.tagContent = tagContent;
        this.category = category;
        this.diary = diary;
        this.sentimentTag = sentimentTag;
    }

}
