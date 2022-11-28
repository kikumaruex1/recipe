package com.example.subcategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

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
    public Subcategory findByName(String name);

    /**
     * カテゴリー情報検索クエリ
     *
     * @param keyword 検索キーワード
     * @return カテゴリー情報のリスト
     */
    @Query("SELECT sc FROM Subcategory sc WHERE sc.name LIKE %?1%")
    public List<Subcategory> search(String keyword);

}
