package com.example.alcohol;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Alcohol;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {

	/**
     * カテゴリー情報の件数取得
     *
     * @param id カテゴリーID
     * @return 取得件数
     */
    public Long countById(Long id);

    /**
     * カテゴリー情報検索クエリ
     *
     * @param name カテゴリー名
     * @return カテゴリー情報
     */
    public Alcohol findByName(String name);

    /**
     * カテゴリー情報検索クエリ
     *
     * @param keyword 検索キーワード
     * @return カテゴリー情報のリスト
     */
    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    public List<Alcohol> search(String keyword);

}
