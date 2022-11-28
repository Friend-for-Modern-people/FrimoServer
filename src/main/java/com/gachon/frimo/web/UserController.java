package com.gachon.frimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.UserService;
import com.gachon.frimo.web.dto.UserDto;
@Controller
@RequestMapping(path = "/app/user")
public class UserController {
    @Autowired
    private static UserService userService;  
    /*
     * 회원가입 API
     * 
     * @RequestBody UserDto.registRequestDto
     * 
     * @return  201 CREATED , saved
     */
    @PostMapping(path = "")
    public ResponseEntity<String> RegistUser (@RequestBody UserDto.RegistRequestDto regist){
        userService.UserRegist(regist);
        return ResponseEntity.status(HttpStatus.CREATED). body("created");
    }
    /*
     * 닉네임 중복확인 API
     * 
     * @param usernickname
     * 
     * @return ok
     */
    @GetMapping(path = "/{userNN}")
    public ResponseEntity<Boolean> checkUserNNDuplicate(@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkUserNNDuplicate(nickname));
    }
    /*
     * 회원탈퇴 API
     * 
     * @param userPk
     * 
     * @return ok
     */
    @DeleteMapping(path = "/{userPK}")
    public ResponseEntity<B>
}
