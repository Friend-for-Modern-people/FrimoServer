package com.gachon.frimo.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.CommentAIService;
import com.gachon.frimo.web.dto.CommentAIDto;
import com.gachon.frimo.web.dto.CommentAIDto.GetCommentResponseDto;

@Controller
@RequestMapping(path="/app/comment")
public class CommentAIController {

    @Autowired
    private static CommentAIService commentAIService;  
    /*
     * 해당 일기의 댓글을 가져오는 API
     * 
     * @param PathVariable Long postPk
     * 
     * @return  List<CommentDto.GetCommentResponseDto>
     * 
     */
    @GetMapping(path="/{poskPK}")
    public ResponseEntity<List<GetCommentResponseDto>> addComment (@PathVariable(value ="postPk") Long diaryPk){
      List<CommentAIDto.GetCommentResponseDto> getcomments = commentAIService.getCommentInDiary(diaryPk);

      return ResponseEntity.status(HttpStatus.OK).body(getcomments);
    }

      /*
     * 해당 게시글의 댓글을 등록하는 API
     * 
     * @param RequestBody {String commentContent, LocalDataTime commentDate, Diary diary}
     * 
     * @return  201 CREATED , saved
     */
    @PostMapping()
    public ResponseEntity<String> addRegion(@RequestBody  CommentAIDto.AddCommentRequestDto addCommentRequestDto) {
        commentAIService.addComment(addCommentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("saved");
    }
    
}
