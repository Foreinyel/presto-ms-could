package com.presto.op.plantBook.web;

import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shihao on 16/12/13.
 */
@RestController
@RequestMapping("/plantBook")
public class PlantBookController {

    @Autowired
    private PlantBookReqService plantBookReqService;

    @RequestMapping(value = "/listAllNewReqs", method = RequestMethod.GET)
    public JsonResult findAllNewReqs() {
        return JsonResult.success(plantBookReqService.findAllNewReqs());
    }

    @RequestMapping(value = "/reqApproval", method = RequestMethod.GET)
    public JsonResult reqApproval(@RequestParam Long reqId) {
        if (reqId == null) {
            return JsonResult.failure("植书申请ID不能为空");
        }
        PlantBookReqVO plantBookReqVO = new PlantBookReqVO();
        plantBookReqVO.setId(reqId);
        return JsonResult.success(plantBookReqService.plantBookReqApproval(plantBookReqVO));
    }

    @RequestMapping(value = "/reqReject", method = RequestMethod.GET)
    public JsonResult reqReject(@RequestParam Long reqId) {
        if (reqId == null) {
            return JsonResult.failure("植书申请ID不能为空");
        }
        PlantBookReqVO plantBookReqVO = new PlantBookReqVO();
        plantBookReqVO.setId(reqId);
        return JsonResult.success(plantBookReqService.plantBookReqReject(plantBookReqVO));
    }

    @RequestMapping(value = "/updateReq", method = RequestMethod.POST)
    public JsonResult updateReq(@RequestBody PlantBookReqVO vo) {
        if (vo.getId() == null) {
            return JsonResult.failure("植书申请ID不能为空");
        }
        if (vo.getBookName() == null) {
            return JsonResult.failure("书籍名称不能为空");
        }
        if (vo.getBookAuthor() == null) {
            return JsonResult.failure("书籍作者不能为空");
        }
        if (vo.getBookIsbn() == null) {
            return JsonResult.failure("ISBN不能为空");
        }
        if (vo.getBookPress() == null) {
            return JsonResult.failure("出版社不能为空");
        }
        return JsonResult.success(plantBookReqService.updatePlantBookReq(vo));
    }


}
