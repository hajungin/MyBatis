package com.example.mybatisTest.controller;

import com.example.mybatisTest.dto.BoardDTO;
import com.example.mybatisTest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;
    @GetMapping("/")
    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
//    루트로 들어와도 걸리고 아무것도 안 적어도 걸리고 , 스프링버전이 5 미만이면 @RequestMapping 사용을 이렇게
    public String index(){
        return "index";
    }

    @GetMapping("save")
    public String save(){
        return "save";
    }

    @PostMapping("save")
    public String saveBoard(BoardDTO boardDTO){
        System.out.println("boardDto : " + boardDTO.toString());
        boardService.save(boardDTO);
        return "save";
    }


}
