package com.example.alcohol;

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

import com.example.entity.Alcohol;

@Controller
@RequestMapping("/alcohols")
public class AlcoholController {

	private final AlcoholService alcoholService;

	@Autowired
	public AlcoholController(AlcoholService alcoholService) {
		this.alcoholService = alcoholService;
	}

	@GetMapping
	public String listAlcohols(@RequestParam(required = false)String keyword, Model model )
	{
		List<Alcohol> listAlcohols = alcoholService.listAll(keyword);
		model.addAttribute("listAlcohols",listAlcohols);
		model.addAttribute("keyword",keyword);
		return "alcohols/alcohols";

	}

	@GetMapping("/new")
	public String newAlcohol(Model model)
	{
		Alcohol alcohol = new Alcohol();
		model.addAttribute("alcohol", alcohol);
		return "alcohols/alcohol_form";
	}

	@PostMapping("/save")
	public String saveAlcohol(Alcohol alcohol, RedirectAttributes ra)
	{
		alcoholService.save(alcohol);
		ra.addFlashAttribute("message", "登録に成功しました");
		return "redirect:/alcohols";
	}

	@GetMapping("/detail/{id}")
    public String detailCategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        try {
        	Alcohol alcohol = alcoholService.get(id);
            model.addAttribute("alcohol", alcohol);
            return "alcohols/alcohol_detail";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
            return "redirect:/alcohols";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        try {
        	Alcohol alcohol = alcoholService.get(id);
            model.addAttribute("alcohol", alcohol);
            return "alcohols/alcohol_edit";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
            return "redirect:/alcohols";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
        try {
        	alcoholService.delete(id);
            ra.addFlashAttribute("success_message", "削除に成功しました");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
        }
        return "redirect:/alcohols";
    }

}


