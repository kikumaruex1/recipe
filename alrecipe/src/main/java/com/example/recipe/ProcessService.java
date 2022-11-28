package com.example.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Process;
import com.example.entity.Recipe;

@Service
public class ProcessService {

	private final ProcessRepository processRepository;

	public ProcessService( ProcessRepository processRepository) {
		this.processRepository = processRepository;
	}

	/**
     * 手順情報全件取得
     *
     * @return 手順情報のリスト
     */
    public List<Process> listAll() {
        return processRepository.findAll();
    }

    /**
     * IDに紐づく手順情報取得処理
     *
     * @param id 手順ID
     * @return 手順情報
     */
    public Process get(Long id) {
        return processRepository.findById(id).get();
    }

    /**
     * IDに紐づく手順情報取得処理
     *
     * @param id 手順ID
     * @return 手順情報
     */
    public void delete(Long id) {
    	processRepository.deleteById(id);
	}

//    /**
//     * レシピIDにあった手順情報取得処理
//     *
//     * @param keyword 検索キーワード
//     * @return 手順情報のリスト
//     */
//    public List<Process> listAll(Recipe id , Process recipeid) {
//        // レシピIDに一致する手順テーブルのレシピIDがある場合
//        if (id.equals(recipeid)) {
//            return  processRepository.search(id);
//        }
//        // それ以外の場合
//        else {
//            return  processRepository.findAll();
//        }
//    }
//
//    /**
//     * IDに紐づく手順情報取得処理
//     *
//     * @param id 手順ID
//     * @return 手順情報
//     */
//    public Process getRecipe(Long recipeid) {
//        return processRepository.findById(recipeid).get();
//    }

}
