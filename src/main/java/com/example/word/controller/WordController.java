package com.example.word.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.word.form.WordForm;
import com.example.word.form.WordPageForm;
import com.example.word.service.IWordService;
import com.example.word.vo.Result;
import com.example.word.vo.WordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuyuhui
 * @since 2021-05-12
 */
@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    private IWordService wordService;

    /**
     * 根据条件获取单词列表
     *
     * @param form
     */
    @PostMapping("/listQuery")
    public Result listQuery(@RequestBody WordPageForm form) {
        Page<WordVo> wordVoPage = wordService.pageQuery(form);
        if (wordVoPage == null || wordVoPage.getRecords() == null) {
            return Result.failure();
        }
        wordVoPage.setRecords(confuseOrder(wordVoPage.getRecords()));
        return Result.success(wordVoPage);
    }

    /**
     * 打乱顺序
     *
     * @param list
     * @return
     */
    private LinkedList<WordVo> confuseOrder(List<WordVo> list) {
        LinkedList<WordVo> result = new LinkedList<>();
        // 打乱顺序
        Random random = new Random();
        while (!list.isEmpty()) {
            int i = random.nextInt(list.size());
            result.add(list.remove(i));
        }
        return result;
    }

    /**
     * 批量添加单词
     *
     * @param formList
     * @return
     */
    @PostMapping("/batchAdd")
    public Result batchAdd(@RequestBody List<WordForm> formList) {
        if (formList == null || formList.isEmpty()) {
            return Result.failure("无效表单");
        }
        // 校验必填属性
        for (WordForm form : formList) {
            if (StringUtils.isBlank(form.getEn())) {
                return Result.failure("表单有误");
            }
        }
        Boolean success = wordService.batchAdd(formList);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.failure("添加失败");
    }

    /**
     * 根据id更新单词
     *
     * @param form
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody WordForm form) {
        if (form == null || StringUtils.isBlank(form.getId())) {
            return Result.failure("无效id");
        }
        if (StringUtils.isBlank(form.getEn()) && StringUtils.isEmpty(form.getCn()) && form.getStore() == null) {
            return Result.failure("无效表单");
        }
        if (wordService.update(form)) {
            return Result.success();
        }
        return Result.failure("更新失败");
    }

    /**
     * 根据id删除单词
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        if (id == null || StringUtils.isBlank(id)) {
            return Result.failure("无效id");
        }
        if (wordService.delete(id)) {
            return Result.success();
        }
        return Result.failure("删除失败");
    }

    /**
     * 批量删除单词
     *
     * @param ids
     * @return
     */
    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.failure("无效id列表");
        }
        for (String id : ids) {
            if (id == null || StringUtils.isBlank(id)) {
                return Result.failure("无效id");
            }
        }
        if (wordService.batchDelete(ids)) {
            return Result.success();
        }
        return Result.failure("删除失败");
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id) {
        if (id == null || StringUtils.isBlank(id)) {
            return Result.failure("无效id");
        }
        WordVo vo = wordService.getDetail(id);
        if (vo != null) {
            return Result.success(vo);
        }
        return Result.failure("获取详情失败");
    }

    /**
     * 分页查询
     *
     * @param form
     * @return
     */
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody WordPageForm form) {
        return Result.success(wordService.pageQuery(form));
    }
}

