package com.dawn.dawn.weblink.controller;

import com.dawn.dawn.common.core.aop.OperationLog;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.weblink.entity.Weblink;
import com.dawn.dawn.weblink.param.WeblinkParam;
import com.dawn.dawn.weblink.service.WeblinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Weblink)表控制层
 *
 * @author 陈黎明
 * @since 2024-05-27 13:42:33
 */
@RestController
@RequestMapping("weblink")
public class WeblinkController extends BaseController {

    @Resource
    private WeblinkService weblinkService;

    /**
     * 查询所有
     * @param weblinkParam 参数
     * @return 查询结果
     */
    @GetMapping("/list")
    @OperationLog(module = "web链接",operator = "查询所有")
    public Result<?> list(WeblinkParam weblinkParam) {
        List<Weblink> list = weblinkService.list();
        return success(list);
    }
    
    
    /**
     * 通过id查询单条数据
     *
     * @param id 主键编号
     * @return 单条数据
     */
    @OperationLog(module = "web链接",operator = "查询详情")
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        // 数据查询
        Weblink weblink = weblinkService.getById(id);
        return success(weblink);
    }
    
    /**
     * 新增数据
     * @param weblink 参数
     * @return 新增结果
     */
    @PostMapping
    @OperationLog(module = "web链接",operator ="新增数据" )
    public Result<?> save(@RequestBody Weblink weblink) {
        if (!weblinkService.save(weblink)) {
            return fail("新增失败");
        }
        return success("新增成功");
    }
    
    /**
     * 修改数据
     *
     * @param weblink 参数
     * @return 修改结果
     */
    @PutMapping
    @OperationLog(module = "web链接",operator = "修改数据")
    public Result<?> update(@RequestBody Weblink weblink) {
        if (!weblinkService.updateById(weblink)) {
            return fail("更新失败");
        }
        return success("更新成功");
    }
    
    /**
     * 根据id删除数据
     *
     * @param id 编号
     * @return 删除结果
     */
    @OperationLog(module = "web链接",operator = "删除数据")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") String id) {
        if (!weblinkService.removeById(id)) {
            return fail("删除失败");
        }
        return success("删除成功");
    }
}

