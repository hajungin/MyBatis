package com.example.mybatisTest.mapper;

import com.example.mybatisTest.dto.BoardDTO;
import com.example.mybatisTest.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void save(@Param("boardDto")BoardDTO boardDTO);

    List<BoardDTO> findAll();

    BoardDTO findOne(@Param("id") Long id);

    void upHits(@Param("id") Long id);

    BoardDTO update(@Param("id") Long id);

    void delete(@Param("id") Long id);

    void update(@Param("boardFile") BoardDTO boardDTO);

    void saveFile(BoardFileDTO boardFileDTO);
}
