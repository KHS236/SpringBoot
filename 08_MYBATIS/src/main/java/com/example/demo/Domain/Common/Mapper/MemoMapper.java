package com.example.demo.Domain.Common.Mapper;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper //매퍼 어노테이션 붙이기
public interface MemoMapper {
//    @Insert("인설트 할 거면 인설트 어노테이션에 쿼리문 작성")
//    @SelectKey(statement = "쿼리문SELECT max(id)+1(다음id구하기+1) FROM testdb.tbl_memo",keyProperty="id",before =얘 뭐임?,resultType = 반환자료형 )
    @SelectKey(statement = "SELECT max(id) FROM testdb.tbl_memo",keyProperty = "id" ,before = false,resultType = Long.class)
    @Insert("insert into tbl_memo values(#{id},#{text},#{writer},#{createAt})")
    //MYBATIS는 쿼리문에 ? 대신 #{} 처리
    public int insert (MemoDto memoDto); //JPA의 JPQL방식과 유사
    // sqlSession의 insert와 연결됨

//    @Update("update tbl_memo set 컬럼명=값,컬럼명=값 where id=#{id}")
    @Update("update tbl_memo set text=#{text},writer=#{writer} where id=#{id}")
    public int update(MemoDto memoDto);

//    @Delete("delete from tbl_memo where id=#{id}")
    @Delete("delete from tbl_memo where id=#{id}")//memoDto의 id가 여기id로 들어와서 아래로
    public int delete(@Param("id") Long memoid);

    @Select("select * from tbl_memo")
    public List<MemoDto> selectAll();

    @Select("select * from tbl_memo where %{type} like concat('%',#{keyword},'%')")
    public List<MemoDto> selectAllContains(String type,String keyword);

    @Results(id="MemoResultMap",value={
            @Result(property = "text",column = "text"), //property 리절트 맵에 들어가는 속성명
            @Result(property = "writer",column = "writer")
    })//ResultMap 어노테이션을 사용하면 원하는 컬럼의 값만 가져오기
    @Select("select text,writer from tbl_memo")
    public List<Map <String,Object> > selectAllWithResultMap();
    //Results 어노테이션의 value값이 String,Object로 받아진다


    //=================================================================
    //XML 방식
    public int insertXML (MemoDto memoDto);
    public int updateXML(MemoDto memoDto);
    public int deleteXML(@Param("id") Long memoid);
    //select







}
