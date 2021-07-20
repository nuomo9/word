package com.example.word.form;


/**
 * 添加单词表单
 *
 * @author someone
 */
public class WordForm {
    /**
     * 主键
     */
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "WordForm{" +
                "id='" + id + '\'' +
                ", en='" + en + '\'' +
                ", cn='" + cn + '\'' +
                ", store=" + store +
                '}';
    }
}
