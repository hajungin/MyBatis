package com.example.mybatisTest.mapper;

import com.example.mybatisTest.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    Long save(@Param("boardDto")BoardDTO boardDTO);

    List<BoardDTO> findAll();

    BoardDTO findOne(@Param("id") Long id);

    void upHits(@Param("id") Long id);

    BoardDTO update(@Param("id") Long id);

    void delete(@Param("id") Long id);

    void update(BoardDTO boardDTO);
}
