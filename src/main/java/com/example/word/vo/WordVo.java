package com.example.word.vo;

/**
 * 单词视图
 *
 * @author someone
 */
public class WordVo {
    /**
     * 主键字符串
     */
    private String idStr;

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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
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
