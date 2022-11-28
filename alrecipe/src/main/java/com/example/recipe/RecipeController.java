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

import com.example.alcohol.AlcoholService;
import com.example.category.CategoryService;
import com.example.entity.Alcohol;
import com.example.entity.Category;
import com.example.entity.Recipe;
import com.example.entity.Subcategory;
import com.example.entity.Process;
import com.example.subcategory.SubcategoryService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	private final AlcoholService alcoholService;

    private final SubcategoryService subcategoryService;

    private final CategoryService categoryService;

    private final ProcessService processService;

	private final RecipeService recipeService;


	@Autowired
	public RecipeController(
			AlcoholService alcoholService,
			SubcategoryService subcategoryService,
			CategoryService categoryService,
			ProcessService processService,
			RecipeService recipeService)
	{
		this.alcoholService = alcoholService;
		this.subcategoryService = subcategoryService;
		this.categoryService = categoryService;
		this.processService = processService;
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

    /**
     * レシピ詳細画面表示
     *
     * @param recipe レシピ情報
     * @param model
     * @return レシピ詳細画面
     */

	@GetMapping("/detail/{id}")
	public String detailRecipe(@PathVariable(name = "id") Long id ,Model model)
	{
		//レシピIDに紐づけてレシピ情報を取得
		Recipe recipe = recipeService.get(id);

		model.addAttribute("recipe",recipe);

		//カテゴリー＋サブカテゴリー＋アルコール情報も取得
		List<Alcohol> listAlcohols = alcoholService.listAll();
		List<Category> listCategories = categoryService.listAll();
		List<Subcategory> listSubcategories = subcategoryService.listAll();

		model.addAttribute("listAlcohols", listAlcohols);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listSubcategories", listSubcategories);

		//手順IDのレシピIDに紐づけて手順情報を取得
		//Process process = processService.get(id);
		//if(process.equals(recipe)) {
			List<Process> listProcesses = processService.listAll();
			model.addAttribute("listProcesses", listProcesses);
		//}
		//レシピ詳細ページに遷移
		return "recipes/recipe_detail";
	}

	/**
     * レシピ削除処理
     *
     * @param recipe レシピ情報
     * @param model
     * @return レシピ詳細画面
     */
	@GetMapping("delete/{id}")
	public String deleteRecipe(@PathVariable(name = "id") Long id , Model model, RedirectAttributes ra)
	{
		//カテゴリー情報削除
		recipeService.delete(id);
		//削除成功メッセージが表示
		ra.addFlashAttribute("success_message", "削除に成功しました");
		return "redirect:/recipes";


	}


}
