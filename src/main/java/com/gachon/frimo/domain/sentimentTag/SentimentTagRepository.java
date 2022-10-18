package com.gachon.frimo.domain.sentimentTag;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentimentTagRepository extends JpaRepository<SentimentTag, Long> {
    public SentimentTag findBySentPk(Long sentPk);
}
