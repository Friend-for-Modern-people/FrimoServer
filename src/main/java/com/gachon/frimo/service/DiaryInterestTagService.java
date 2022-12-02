package com.gachon.frimo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTag;
import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTagRepository;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;
import com.gachon.frimo.domain.sentimentTag.SentimentTagRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.DiaryInterestTagDto;
import com.gachon.frimo.web.dto.DiaryInterestTagDto.GetTagResponseDto;

public class DiaryInterestTagService {
    @Autowired
    private static DiaryInterestTagRepository diaryInterestTagRepository;
    @Autowired
    private static DiaryRepository diaryRepository;
    @Autowired
    private static SentimentTagRepository sentimentTagRepository;

    @Transactional
    // 해당 일기의 태그에서 감정만 뽑아 계산하기, 가장 많은 감정을 반환 
    public String getMainSent(Long diaryPk) {

        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<DiaryInterestTag> tags = diaryInterestTagRepository.findAllByDiary(diary);

        List<DiaryInterestTag> angerlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 0))
                .collect(Collectors.toList());
        List<DiaryInterestTag> sadlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 1))
                .collect(Collectors.toList());
        List<DiaryInterestTag> anxilist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 2))
                .collect(Collectors.toList());
        List<DiaryInterestTag> hurtlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 3))
                .collect(Collectors.toList());
        List<DiaryInterestTag> embarlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 4))
                .collect(Collectors.toList());
        List<DiaryInterestTag> haplist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 5))
                .collect(Collectors.toList());

        int anger = angerlist.size(); // 0
        int sadness = sadlist.size(); // 1
        int anxiety = anxilist.size(); // 2
        int hurts = hurtlist.size(); // 3
        int embarrassed = embarlist.size(); // 4
        int happiness = haplist.size(); // 5
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("anger", anger);
        map.put("sadness", sadness);
        map.put("anxiety", anxiety);
        map.put("hurts", hurts);
        map.put("embarrassed", embarrassed);
        map.put("happiness ", happiness );

        return Collections.max(map.keySet());

    }

    // 테그를 추가 ( 모델의 아웃풋으로 나오는 태그도 저장할 수 있을까..?)
    @Transactional
    public void addTag(DiaryInterestTagDto.AddTagRequestDto tag) {
        Diary diary = diaryRepository.findByDiaryPk(tag.getDiaryPk());
        SentimentTag sentimentTag = sentimentTagRepository.findBySentPk(tag.getSentPK());
        DiaryInterestTag newTag = DiaryInterestTag.builder()
                .tagContent(tag.getTagContent())
                .category(tag.getCategory())
                .diary(diary)
                .sentimentTag(sentimentTag)
                .build();
        diaryInterestTagRepository.save(newTag);
    }

    // 일기에 속한 모든 테그를 가져오기
    @Transactional
    public List<DiaryInterestTagDto.GetTagResponseDto> getTags (Long diaryPk){
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<DiaryInterestTag> tags = diaryInterestTagRepository.findAllByDiary(diary);

        return  tags.stream().map(DiaryInterestTagDto::toGetTagResponseDto)
                .collect(Collectors.toList());

    }

}
