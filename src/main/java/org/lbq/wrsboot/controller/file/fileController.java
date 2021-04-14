package org.lbq.wrsboot.controller.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lbq.wrsboot.Vo.PageVo;
import org.lbq.wrsboot.Vo.RespVo;
import org.lbq.wrsboot.bean.Files;
import org.lbq.wrsboot.service.impl.FileServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RequestMapping("/file")
@RestController
public class fileController {

    @Resource
    FileServiceImpl fileServiceimpl;

    @GetMapping("/all")
    public RespVo getFileLists(@RequestParam(name ="size",required=true,defaultValue = "1") Integer size
                               ,@RequestParam(name="num",required = true,defaultValue = "10")Integer num) throws JsonProcessingException {
        PageHelper.startPage(num,size);
        List<Files> files = fileServiceimpl.queryAllFiles();
        PageInfo<Files> pageInfo = new PageInfo(files);
        PageVo pageVo = new PageVo(pageInfo.getPages(), Collections.singletonList(pageInfo.getList()));
        String s = new ObjectMapper().writeValueAsString(pageVo);
        return  new RespVo(200,"查询成功",s);
    }
    
    @PostMapping("/update")
    public RespVo  updateFile(Files file) throws JsonProcessingException {
       fileServiceimpl.updateFile(file.getId(),file);
        String sa = new ObjectMapper().writeValueAsString(file);
        return  new RespVo(200,"修改成功",sa);
    }

    @GetMapping("/delete")
    public RespVo  deleteFile(Integer  id){
        int i = fileServiceimpl.deleteFile(id);
        return i>0? new RespVo(200,"删除成功",id):new RespVo(500,"删除失败",id);

    }

    //查询文件上传的记录
     @GetMapping("/key")
    public int  getFileIndex(String key){
         Integer fileIndex= fileServiceimpl.getFileIndex(key);
             return  fileIndex;
     }

}
