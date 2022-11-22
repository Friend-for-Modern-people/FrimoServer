package com.gachon.frimo.domain.diaryInterestTag;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachon.frimo.domain.sentimentTag.SentimentTag;


@Repository
public interface DiaryInterestTagRepository extends JpaRepository<DiaryInterestTag, Long>{
    public Optional<DiaryInterestTag> findByCategory(String category);
    public Optional<DiaryInterestTag> findByTagContent(String tagContent);
    public Optional<DiaryInterestTag> findBySentimentTag(SentimentTag sentimentTag);
    
}
