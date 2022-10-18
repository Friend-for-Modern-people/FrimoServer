package com.gachon.frimo.domain.commentAI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentAIRepository extends JpaRepository<CommentAI, Long> {
    // 날짜별 comment 가져오기
    public Optional<CommentAI> findByCommentDate(LocalDateTime commentDate);
}
