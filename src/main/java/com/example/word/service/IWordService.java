package com.example.word.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.word.entity.Word;
import com.example.word.form.WordForm;
import com.example.word.form.WordPageForm;
import com.example.word.vo.WordVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuyuhui
 * @since 2021-05-12
 */
public interface IWordService extends IService<Word> {

    /**
     * 批量添加单词
     *
     * @param formList
     * @return
     */
    Boolean batchAdd(List<WordForm> formList);

    /**
     * 根据id更新单词
     *
     * @param form
     * @return
     */
    boolean update(WordForm form);

    /**
     * 根据id删除单词
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 批量删除单词
     *
     * @param ids
     * @return
     */
    boolean batchDelete(List<String> ids);

    /**
     * 根据获取单词详情
     *
     * @param id
     * @return
     */
    WordVo getDetail(String id);

    /**
     * 分页查询单词
     *
     * @param form
     * @return
     */
    Page<WordVo> pageQuery(WordPageForm form);
}
