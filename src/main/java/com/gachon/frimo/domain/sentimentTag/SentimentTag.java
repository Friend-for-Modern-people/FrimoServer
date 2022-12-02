package com.gachon.frimo.domain.sentimentTag;

import java.io.Serializable;

import com.gachon.frimo.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTag;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;
import com.gachon.frimo.domain.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sentiment_tag")
public class SentimentTag extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "sent_pk")
    private Long sentPk;

    @Column(name="sent_large_id")
    private int sentLargeId;

    @Column(name ="sent_small_id", length=10)
    private String sentSmallId;

    @OneToMany(fetch = FetchType.LAZY , mappedBy="sentimentTag")
    private List<DiaryInterestTag> tags;

    @Builder
    public SentimentTag(int sentLargeId, String sentSmallId){
        this.sentLargeId = sentLargeId;
        this.sentSmallId = sentSmallId;
    }
}
