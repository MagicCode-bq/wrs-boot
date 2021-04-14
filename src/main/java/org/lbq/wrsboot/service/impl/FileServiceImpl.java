package org.lbq.wrsboot.service.impl;


import org.lbq.wrsboot.bean.Files;
import org.lbq.wrsboot.mapper.FileMapper;
import org.lbq.wrsboot.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
*/
@Service
public class FileServiceImpl implements FileService{


    @Resource
    FileMapper fileMapper;


    @Override
    public List<Files> queryAllFiles() {
        return fileMapper.queryAllFiles();
    }

    @Override
    public int insertFile(Files file) {
         //查询文件是否在数据库中存在
        Files files = queryFileKey(file.getFilekey());
        if(files!=null){
            files.setShardindex(file.getShardindex()); //修改上传的片段
            files.setUpdatetime( String.valueOf(System.currentTimeMillis()));
            System.out.println(files);
            fileMapper.updateFile(files.getId(),files);
            return 1;
        }else{
            return fileMapper.insertFile(file);
        }
    }

    @Override
    public void updateFile(Integer id, Files file) {
           fileMapper.updateFile(id,file);
    }

    @Override
    public int deleteFile(Integer id) {
        return fileMapper.deleteFile(id);

    }

    //查询文件记录
     public Files queryFileKey(String fileKey){
        return  fileMapper.queryFilesKey(fileKey);
     }


    public Integer getFileIndex(String key) {
        Files files = queryFileKey(key);
        if(files!=null){
            return  files.getShardindex();
        }
        return 0;
    }
}
