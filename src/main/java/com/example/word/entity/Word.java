package com.example.word.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuyuhui
 * @since 2021-07-18
 */
@TableName("word")
public class Word extends Model<Word> {


    /**
     * 主键排序
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单词英文
     */
    @TableField("en")
    private String en;

    /**
     * 单词中文
     */
    @TableField("cn")
    private String cn;

    /**
     * 是否收藏; 收藏1,未收藏0
     */
    @TableField("store")
    private Boolean store;

    /**
     * 逻辑删除标识; 1已删除,0未删除
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", en=" + en +
                ", cn=" + cn +
                ", store=" + store +
                ", deleted=" + deleted +
                "}";
    }
}
