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
     * ブランド情報全件取得
     *
     * @return ブランド情報のリスト
     */
    public List<Recipe> listAll() {
        return recipeRepository.findAll();
    }

    /**
     * ブランド情報検索処理
     *
     * @param keyword 検索キーワード
     * @return ブランド情報のリスト
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
     * IDに紐づくブランド情報取得処理
     *
     * @param id ブランドID
     * @return ブランド情報
     */
    public Recipe get(Long id) {
        return recipeRepository.findById(id).get();
    }

    /**
     * ブランド情報登録処理
     *
     * @param recipe 保存したいブランド情報
     * @return 保存したブランド情報
     */
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /**
     * IDに紐づくブランド情報削除処理
     *
     * @param id ブランドID
     */
    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
