package com.example.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.subcategory.SubcategoryRepository;
import com.example.entity.Subcategory;

@Service
public class SubcategoryService {

	private final SubcategoryRepository subcategoryRepository;

	@Autowired
	public SubcategoryService(SubcategoryRepository subcategoryRepository) {
		this.subcategoryRepository = subcategoryRepository;
	}


	/**
     * サブカテゴリー情報全件取得
     *
     * @return カテゴリー情報のリスト
     */
    public List<Subcategory> listAll() {
        return subcategoryRepository.findAll();
    }

    /**
     * サブカテゴリー情報検索処理
     *
     * @param keyword 検索キーワード
     * @return カテゴリー情報のリスト
     */
    public List<Subcategory> listAll(String keyword) {
        // 検索キーワードがあった場合
        if (keyword != null && !keyword.isEmpty()) {
            return subcategoryRepository.search(keyword);
        }
        // それ以外の場合
        else {
            return subcategoryRepository.findAll();
        }
    }

    /**
     * IDに紐づくサブカテゴリー情報取得処理
     *
     * @param id カテゴリーID
     * @return カテゴリー情報
     * @throws NotFoundException
     */
    public Subcategory get(Long id) throws NotFoundException {
        // IDに紐づくカテゴリー情報が存在するかの確認
        if (!this.exists(id)) {
            throw new NotFoundException();
        }
        return subcategoryRepository.findById(id).get();
    }

    /**
     * サブカテゴリー情報登録処理
     *
     * @param subcategory 保存したいカテゴリー情報
     * @return 保存したカテゴリー情報
     */
    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    /**
     * サブカテゴリー情報の入力値チェック
     *
     * @param brand 保存したいカテゴリー情報
     * @return true:正常の入力値 false:異常な入力値
     */
    public boolean isValid(Subcategory subcategory) {
        // 保存したいカテゴリー名の文字数を取得
        int nameLength = subcategory.getName().length();

        // 文字数の判定（1文字から32文字まで）
        if (nameLength < 1 || nameLength > 32) {
            return false;
        }
        return true;
    }

    /**
     * カテゴリー名の重複チェック
     *
     * @param name 重複確認したいブランド情報
     * @return true:重複なし false:重複あり
     */
    public boolean checkUnique(Subcategory subcategory) {
        boolean isCreatingNew = (subcategory.getId() == null || subcategory.getId() == 0);
        Subcategory subcategoryByName = subcategoryRepository.findByName(subcategory.getName());

        if (isCreatingNew) {
            if (subcategoryByName != null) {
                return false;
            }
        } else {
            if (subcategoryByName != null && subcategoryByName.getId() != subcategory.getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * IDに紐づくカテゴリー情報削除処理
     *
     * @param id カテゴリーID
     * @throws NotFoundException
     */
    public void delete(Long id) throws NotFoundException {
        // IDに紐づくカテゴリー情報が存在するかの確認
        if (!this.exists(id)) {
            throw new NotFoundException();
        }
        subcategoryRepository.deleteById(id);
    }

    /**
     * カテゴリー情報の存在チェック
     *
     * @param name 確認したいカテゴリー情報のID
     * @return true:存在する false:存在しない
     */
    private boolean exists(Long id) {
        Long countById = subcategoryRepository.countById(id);
        if (countById == null || countById == 0L) {
            return false;
        }
        return true;
    }

}
