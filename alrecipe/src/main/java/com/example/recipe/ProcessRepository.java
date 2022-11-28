package com.example.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Process;
import com.example.entity.Recipe;


public interface ProcessRepository extends JpaRepository<Process, Long> {

	/**
     * 手順情報の件数取得
     *
     * @param id 手順ID
     * @return 取得件数
     */
    public Long countById(Long id);

    /**
     * 手順情報検索クエリ
     *
     * @param name 手順名
     * @return 手順情報
     */
    public Process findByProcess(String process);

    /**
     * 手順情報から商品情報の件数取得
     *
     * @param recipeid レシピID
     * @return 取得件数
     */
    //public Long countByRecipeId(Long recipeid);

    //public List<Process> search(Recipe id);

}
