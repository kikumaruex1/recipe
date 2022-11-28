package com.example.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Subcategory;

@Controller
@RequestMapping("subcategories")
public class SubcategoryController {

	private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    /**
     * サブカテゴリー一覧画面表示
     *
     * @param model
     * @return サブカテゴリー一覧画面
     */
    @GetMapping
    public String listSubcategories(@RequestParam(required = false) String keyword, Model model) {
        // 全カテゴリー情報の取得
        List<Subcategory> listSubcategories = subcategoryService.listAll(keyword);
        model.addAttribute("listSubcategories", listSubcategories);
        model.addAttribute("keyword", keyword);
        return "subcategories/subcategories";
    }

    /**
     * カテゴリー新規登録画面表示
     *
     * @param model
     * @return カテゴリー新規登録画面
     */
    @GetMapping("/new")
    public String newSubcategory(Model model) {
        // 新規登録用に、空のカテゴリー情報作成
        Subcategory subCategory = new Subcategory();
        model.addAttribute("subCategory", subCategory);
        return "subcategories/category_form";
    }

    /**
     * カテゴリー登録・更新処理
     *
     * @param subCategory カテゴリー情報
     * @param ra
     * @return カテゴリー一覧画面
     */
    @PostMapping("/save")
    public String saveSubcategory(Subcategory subCategory, RedirectAttributes ra) {

        // カテゴリー情報の登録
        subcategoryService.save(subCategory);
        // 登録成功のメッセージを格納
        ra.addFlashAttribute("success_message", "登録に成功しました");
        return "redirect:/subcategories";
    }

    /**
     * カテゴリー詳細画面表示
     *
     * @param id カテゴリーID
     * @param model
     * @param ra
     * @return カテゴリー詳細画面
     */
    @GetMapping("/detail/{id}")
    public String detailSubcategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        try {
            // カテゴリーIDに紐づくカテゴリー情報取得
            Subcategory subCategory = subcategoryService.get(id);
            model.addAttribute("subCategory", subCategory);
            return "subcategories/subcategory_detail";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
            return "redirect:/subcategories";
        }
    }

    /**
     * カテゴリー編集画面表示
     *
     * @param id カテゴリーID
     * @param model
     * @param ra
     * @return カテゴリー編集画面
     */
    @GetMapping("/edit/{id}")
    public String editSubcategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        try {
            // カテゴリーIDに紐づくカテゴリー情報取得
            Subcategory subCategory = subcategoryService.get(id);
            model.addAttribute("subCategory", subCategory);
            return "subcategories/subcategory_edit";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
            return "redirect:/subcategories";
        }
    }

    /**
     * カテゴリー削除処理
     *
     * @param id カテゴリーID
     * @param model
     * @param ra
     * @return カテゴリー一覧画面
     */
    @GetMapping("/delete/{id}")
    public String deleteSubcategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        // カテゴリー情報削除
        try {
            subcategoryService.delete(id);
            ra.addFlashAttribute("success_message", "削除に成功しました");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
        }
        return "redirect:/subcategories";
    }

}
