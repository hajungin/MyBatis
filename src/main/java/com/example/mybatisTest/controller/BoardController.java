package com.example.mybatisTest.controller;

import com.example.mybatisTest.dto.BoardDTO;
import com.example.mybatisTest.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class BoardController {
    @Autowired
    BoardService boardService;
    @GetMapping("/")
    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
//    루트로 들어와도 걸리고 아무것도 안 적어도 걸리고 , 스프링버전이 5 미만이면 @RequestMapping 사용을 이렇게
    public String index(){
        return "index";
    }

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String saveBoard(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDto : " + boardDTO.toString());
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "list";
    }

    @GetMapping("/one/{id}")
    public String oneList(Model model,
                          @PathVariable("id")Long id){
//        조회수 처리
        boardService.findUpHit(id);
//        상세 내용 처리
        BoardDTO boardDTO1 = boardService.findOne(id);

       log.info(boardDTO1.toString());
        model.addAttribute("board",boardDTO1);
        return "show";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id")Long id,
                         Model model){
        BoardDTO boardDTO = boardService.findOne(id);
        log.info(boardDTO.toString());
        model.addAttribute("board",boardDTO);
        return "update";
    }

    @PostMapping("update/{id}")
    public String update(BoardDTO boardDTO){
         boardService.update(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")Long id){
        boardService.delete(id);
        return "redirect:/list";

    }
}
