package com.example.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Recipe;

@Service
public class RecipeService
{
	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository)
	{
		this.recipeRepository = recipeRepository;
	}

	/**
     * レシピ情報全件取得
     *
     * @return レシピ情報のリスト
     */
    public List<Recipe> listAll() {
        return recipeRepository.findAll();
    }

    /**
     * レシピ情報検索処理
     *
     * @param keyword 検索キーワード
     * @return レシピ情報のリスト
     */
    public List<Recipe> listAll(String keyword) {
        // 検索キーワードがあった場合
        if (keyword != null && !keyword.isEmpty()) {
            return recipeRepository.search(keyword);
        }
        // それ以外の場合
        else {
            return recipeRepository.findAll();
        }
    }

    /**
     * IDに紐づくレシピ情報取得処理
     *
     * @param id レシピID
     * @return レシピ情報
     */
    public Recipe get(Long id) {
        return recipeRepository.findById(id).get();
    }

    /**
     * IDに紐づくレシピ情報取得処理
     *
     * @param id レシピID
     * @return レシピ情報
     */
    public void delete(Long id) {
    	recipeRepository.deleteById(id);
	}

    /**
     * IDに紐づくレシピ情報取得処理
     *
     * @param id レシピID
     * @return レシピ情報
     */
    public 	Recipe save(Recipe recipe) {
    	return recipeRepository.save(recipe);
	}
}
