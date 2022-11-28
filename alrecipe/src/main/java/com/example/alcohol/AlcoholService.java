package com.example.alcohol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Alcohol;

@Service
public class AlcoholService {

	private AlcoholRepository alcoholRepository;

	@Autowired
	public AlcoholService (AlcoholRepository alcoholRepository)
	{
		this.alcoholRepository = alcoholRepository;
	}

	/**
     * アルコール情報全件取得
     *
     * @return アルコール情報のリスト
     */
    public List<Alcohol> listAll() {
        return alcoholRepository.findAll();
    }

    /**
     * アルコール情報検索処理
     *
     * @param keyword 検索キーワード
     * @return アルコール情報のリスト
     */
    public List<Alcohol> listAll(String keyword) {
        // 検索キーワードがあった場合
        if (keyword != null && !keyword.isEmpty()) {
            return alcoholRepository.search(keyword);
        }
        // それ以外の場合
        else {
            return alcoholRepository.findAll();
        }
    }

    /**
     * IDに紐づくアルコール情報取得処理
     *
     * @param id アルコールID
     * @return アルコール情報
     * @throws NotFoundException
     */
    public Alcohol get(Long id) throws NotFoundException {
        // IDに紐づくカテゴリー情報が存在するかの確認
        if (!this.exists(id)) {
            throw new NotFoundException();
        }
        return alcoholRepository.findById(id).get();
    }

    /**
     * アルコール情報登録処理
     *
     * @param alcohol 保存したいアルコール情報
     * @return 保存したアルコール情報
     */
    public Alcohol save(Alcohol alcohol) {
        return alcoholRepository.save(alcohol);
    }

    /**
     * アルコール情報の入力値チェック
     *
     * @param brand 保存したいアルコール情報
     * @return true:正常の入力値 false:異常な入力値
     */
    public boolean isValid(Alcohol alcohol) {
        // 保存したいカテゴリー名の文字数を取得
        int nameLength = alcohol.getName().length();

        // 文字数の判定（1文字から32文字まで）
        if (nameLength < 1 || nameLength > 32) {
            return false;
        }
        return true;
    }

    /**
     * アルコール名の重複チェック
     *
     * @param name 重複確認したいアルコール情報
     * @return true:重複なし false:重複あり
     */
    public boolean checkUnique(Alcohol alcohol) {
        boolean isCreatingNew = (alcohol.getId() == null || alcohol.getId() == 0);
        Alcohol alcoholByName = alcoholRepository.findByName(alcohol.getName());

        if (isCreatingNew) {
            if (alcoholByName != null) {
                return false;
            }
        } else {
            if (alcoholByName != null && alcoholByName.getId() != alcohol.getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * IDに紐づくアルコール情報削除処理
     *
     * @param id アルコールID
     * @throws NotFoundException
     */
    public void delete(Long id) throws NotFoundException {
        // IDに紐づくカテゴリー情報が存在するかの確認
        if (!this.exists(id)) {
            throw new NotFoundException();
        }
        alcoholRepository.deleteById(id);
    }

    /**
     * アルコール情報の存在チェック
     *
     * @param name 確認したいカテゴリー情報のID
     * @return true:存在する false:存在しない
     */
    private boolean exists(Long id) {
        Long countById = alcoholRepository.countById(id);
        if (countById == null || countById == 0L) {
            return false;
        }
        return true;
    }

}
