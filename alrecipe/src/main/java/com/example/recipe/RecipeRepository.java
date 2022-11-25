package com.example.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


	/**
     * 商品情報の件数取得
     *
     * @param id 商品ID
     * @return 取得件数
     */
    public Long countById(Long id);

    /**
     * 商品情報検索クエリ
     *
     * @param name 商品名
     * @return 商品情報
     */
    public Recipe findByName(String name);

    /**
     * 商品情報検索クエリ
     *
     * @param keyword 検索キーワード
     * @return 商品情報のリスト
     */
    @Query("SELECT r FROM Recipe r WHERE r.name LIKE %?1% ")
    public List<Recipe> search(String keyword);
}
