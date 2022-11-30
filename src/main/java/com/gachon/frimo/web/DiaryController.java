package com.gachon.frimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.DiaryService;
import com.gachon.frimo.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path="/app/diary/")
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
     * @return  List<DiaryDto.>
     * 
     */
    @GetMapping(value="/{userPk}")
    public ResponseEntity<List<GetCommentResponseDto>> getDiariesDesc(@PathVariable(value="userPk") Long userPk){
        
    }
}
