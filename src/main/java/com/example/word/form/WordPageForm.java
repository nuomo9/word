package com.example.word.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.word.entity.Word;

/**
 * 单词分页表单
 *
 * @author someone
 */
public class WordPageForm {
    /**
     * 封装分页信息
     */
    private Page<Word> page;
    /**
     * 单词英文
     */
    private String en;
    /**
     * 单词中文
     */
    private String cn;
    /**
     * 是否收藏; 收藏1,未收藏0
     */
    private Boolean store;

    public Page<Word> getPage() {
        return page;
    }

    public void setPage(Page<Word> page) {
        this.page = page;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public Boolean getStore() {
        return store;
    }

    public void setStore(Boolean store) {
        this.store = store;
    }
}
