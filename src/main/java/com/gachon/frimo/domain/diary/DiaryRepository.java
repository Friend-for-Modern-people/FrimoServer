package com.gachon.frimo.domain.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachon.frimo.domain.user.User;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    public Diary findByDiaryPk(Long diaryPk);
    public Optional<Diary> findByMainSent(String mainSent);

    public Optional<Diary> findByAuthor(User author);

    @Query("SELECT * FROM ")
    public List<Diary> findAllDesc();

}
