package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.diary.DiaryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
    @Autowired
    private final DiaryRepository diaryRepository;
//List<Tour> tours = tourRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    @Transactional
    public List<DiaryDto.get>

}
