package com.gachon.frimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.DiaryService;

@Controller
@RequestMapping(path="/app/diary/")
public class DiaryController {
 
    @Autowired
    private static DiaryService diaryservive;
    
}
