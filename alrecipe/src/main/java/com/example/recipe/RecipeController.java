package com.example.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Recipe;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService)
	{
		this.recipeService = recipeService;
	}

	/**
     * レシピ一覧画面表示
     *
     * @param keyword
     * @return レシピ一覧画面
     */
	@GetMapping
	public String listRecipes(@RequestParam(required = false) String keyword,Model model) {

		// レシピ情報の取得
		List<Recipe> listRecipes = recipeService.listAll(keyword);
		model.addAttribute("listRecipes",listRecipes);
		model.addAttribute("keyword",keyword);
		// 取得したレシピ情報を画面に渡す
		return "recipes/recipes";
	}

}
