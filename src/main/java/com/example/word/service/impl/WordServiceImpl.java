package com.example.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.word.entity.Word;
import com.example.word.form.WordForm;
import com.example.word.form.WordPageForm;
import com.example.word.mapper.WordMapper;
import com.example.word.service.IWordService;
import com.example.word.vo.WordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuyuhui
 * @since 2021-05-12
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements IWordService {

    @Override
    public Boolean batchAdd(List<WordForm> formList) {
        List<Word> entities = formList.stream().map(form -> {
            Word entity = convertFormToEntity(form);
            // 自动生成主键
            entity.setId(null);
            entity.setDeleted(false);
            return entity;
        }).collect(Collectors.toList());
        return this.saveBatch(entities);
    }

    @Override
    public boolean update(WordForm form) {
        // 校验必要属性
        if (form == null || StringUtils.isBlank(form.getId())) {
            return false;
        }
        Word entity = convertFormToEntity(form);
        // 设置更新字段
        LambdaUpdateWrapper<Word> updateWrapper = new LambdaUpdateWrapper<>();
        if (StringUtils.isNotBlank(entity.getEn())) {
            updateWrapper.set(Word::getEn, entity.getEn().trim());
        }
        if (StringUtils.isNotBlank(entity.getCn())) {
            updateWrapper.set(Word::getCn, entity.getCn().trim());
        }
        if (entity.getStore() != null) {
            updateWrapper.set(Word::getStore, entity.getStore());
        }
        // 设置更新条件
        updateWrapper.eq(Word::getId, form.getId());
        updateWrapper.eq(Word::getDeleted, false);
        return this.update(updateWrapper);
    }

    @Override
    public boolean delete(String id) {
        if (id == null || StringUtils.isBlank(id)) {
            return false;
        }
        LambdaUpdateWrapper<Word> updateWrapper = new LambdaUpdateWrapper<>();
        // 设置更新字段
        updateWrapper.set(Word::getDeleted, true);
        // 设置更新条件
        updateWrapper.eq(Word::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public boolean batchDelete(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        LambdaUpdateWrapper<Word> updateWrapper = new LambdaUpdateWrapper<>();
        // 设置更新字段
        updateWrapper.set(Word::getDeleted, true);
        // 设置更新条件
        updateWrapper.in(Word::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public WordVo getDetail(String id) {
        if (id == null || StringUtils.isBlank(id)) {
            return null;
        }
        Word entity = this.getById(id);
        if (entity == null) {
            return null;
        }
        // 没有补充字段
        return convertEntityToVo(entity);
    }

    @Override
    public Page<WordVo> pageQuery(WordPageForm form) {
        if (form == null) {
            return null;
        }
        LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(form.getEn())) {
            queryWrapper.like(Word::getEn, form.getEn());
        }
        if (StringUtils.isNotBlank(form.getCn())) {
            queryWrapper.like(Word::getCn, form.getCn());
        }
        if (form.getStore() != null) {
            queryWrapper.eq(Word::getStore, form.getStore());
        }
        // 按照主键排序
        queryWrapper.orderByAsc(Word::getId);
        Page<Word> page = form.getPage();
        if (page == null) {
            page = new Page<>();
        }
        return (Page<WordVo>) this.page(page, queryWrapper).convert(this::convertEntityToVo);
    }

    /**
     * 表单转实体
     *
     * @param form
     * @return
     */
    private Word convertFormToEntity(WordForm form) {
        Word word = new Word();
        BeanUtils.copyProperties(form, word);
        return word;
    }

    /**
     * 实体转视图
     *
     * @param entity
     * @return
     */
    private WordVo convertEntityToVo(Word entity) {
        WordVo vo = new WordVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setIdStr(String.valueOf(entity.getId()));
        return vo;
    }
}
