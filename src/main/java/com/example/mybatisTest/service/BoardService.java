package com.example.mybatisTest.service;

import com.example.mybatisTest.dto.BoardDTO;
import com.example.mybatisTest.dto.BoardFileDTO;
import com.example.mybatisTest.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()){
//            첨부파일 없음
            boardDTO.setFileAttached(0);
            boardMapper.save(boardDTO);
        }else {
//            첨부파일 세팅
            boardDTO.setFileAttached(1);
//            게시글 저장 한 다음 저장된 id 값을 활용해 리턴받음.
            boardMapper.save(boardDTO);
            BoardDTO savedBoard = boardMapper.findOne(boardDTO.getId());
            System.out.println(savedBoard);
            for (MultipartFile boardFile : boardDTO.getBoardFile()){
                //            파일 이름 가져오기
                String originalFileName = boardFile.getOriginalFilename();
                System.out.println("originalFileName : " + originalFileName);

                //            저장용 파일이름 생성
                String storedFileName = System.currentTimeMillis() +"-"+
                        originalFileName;
                System.out.println("storedFileName : " + originalFileName);

            //            BoardFileDTO 를 세팅
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());
            //            파일 저장용 폴더(내 컴퓨터)에 파일 저장 처리
                String savePath = "c:/upload_files/" + storedFileName;
                boardFile.transferTo(new File(savePath));
            //            board_file_table DB 테이블에 저장
                boardMapper.saveFile(boardFileDTO);
            }
        }
    }

    public List<BoardDTO> findAll() {
        return boardMapper.findAll();
    }

    public BoardDTO findOne(Long id) {
        return boardMapper.findOne(id);
    }

    public void findUpHit(Long id) {
        boardMapper.upHits(id);
    }

    public void delete(Long id) {
        boardMapper.delete(id);
    }

    public void update(BoardDTO boardDTO) {
        boardMapper.update(boardDTO);
    }
}
