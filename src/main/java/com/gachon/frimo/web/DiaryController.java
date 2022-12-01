package com.gachon.frimo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.DiaryService;
import com.gachon.frimo.service.UserService;
import com.gachon.frimo.web.dto.DiaryDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/app/diary/")
public class DiaryController {

    @Autowired
    private static DiaryService diaryService;
    @Autowired
    private static UserService userService;
    /*
     * 최신순의 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk
     * 
     * @return List<DiaryDto.GetDiaryResponseDto>
     * 
     */
    @GetMapping(value="/{userPk}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiaries(@PathVariable(value="userPk") Long userPk){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiaries(userPk);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 월별 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk , PathVariable int month
     * 
     * @return List<DiaryDto.>
     * 
     */
    @GetMapping(value="/{userPk}/{month}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiariesbyMonth(@PathVariable(value="userPk") Long userPk, @PathVariable(value="month") int month){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiariesByMonth(userPk, month);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 년도별 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk , PathVariable int year
     * 
     * @return List<DiaryDto.>
     * 
     */
    @GetMapping(value="/{userPk}/{year}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiariesYear(@PathVariable(value="userPk") Long userPk, @PathVariable(value="year") int year){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiariesByMonth(userPk, year);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 일기를 등록하는 API
     * 
     * @param RequestBody DiaryDto.AddDiaryRequestDto
     * 
     * @return  201 CREATED , saved
     */
    @PostMapping(value="/{userPk}")
    public ResponseEntity<String> addComment(@RequestBody  DiaryDto.AddDiaryRequestDto addDiaryRequestDto) {
        diaryService.addDiary(addDiaryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("saved");
    }
}