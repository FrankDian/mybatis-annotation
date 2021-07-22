package com.imooc.mybatis.dao;

import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDAO {
  @Select("select * from t_goods where current_price between #{min} and #{max} order by current_price limit 0,#{limit}")
  public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limit") Integer limit);
  
  @Insert("INSERT INTO t_goods(title,sub_title,original_cost,current_price,discount, is_free_delivery, category_id) VALUES (#{title}, #{subTitle}, #{originalCost}, #{currentPrice}, #{discount},#{isFreeDelivery}, #{categoryId})")
  @SelectKey(statement = "select last_insert_id()", before = false, resultType=Integer.class, keyProperty="goodsId")
  public int insert(Goods goods);
  
  @Select("select * from t_goods")
  @Results({
      @Result(property="goodsId",column="goods_id", id = true),
      @Result(property="title", column="title"),
      @Result(property="currentPrice", column="current_price"),
  })
  public List<GoodsDTO> selectAll();
}
