package com.presto.app.plantBook.web;

import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import com.presto.common.constants.CommonConstants;
import com.presto.common.result.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by shihao on 16/11/2.
 */
@RestController
@RequestMapping("/plantBook")
public class PlantBookController {

    @Autowired
    private PlantBookReqService plantBookReqService;

    @RequestMapping(value = "/req",method = RequestMethod.POST)
    public JsonResult saveReq(@Valid @RequestBody PlantBookReqVO vo){

        if(vo.getUserId() == null){
            return JsonResult.failure("用户不能为空");
        }
        if(vo.getPlantType() == null){
            return JsonResult.failure("植书类型不能为空");
        }
        if(vo.getPlantMethod() == null){
            return JsonResult.failure("植书方式不能为空");
        }else{
            if(vo.getPlantMethod() == CommonConstants.PlantBookMethod.MANUAL){
                if(StringUtils.isBlank(vo.getBookAuthor()) || StringUtils.isBlank(vo.getBookIsbn())
                        || StringUtils.isBlank(vo.getBookName()) || StringUtils.isBlank(vo.getBookPress())){
                    return JsonResult.failure("书籍信息不能为空");
                }else if (vo.getBookId() == null){
                    return JsonResult.failure("书籍Id不能为空");
                }
            }
        }

        return JsonResult.success(plantBookReqService.savePlantBookReq(vo));

    }

}
