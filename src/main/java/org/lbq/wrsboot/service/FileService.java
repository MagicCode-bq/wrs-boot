package org.lbq.wrsboot.service;

import org.apache.ibatis.annotations.Param;
import org.lbq.wrsboot.bean.Files;

import java.util.List;

/**
*
*/
public interface FileService {

    //查询文件记录
    List<Files> queryAllFiles();

    //添加一条文件记录
    int  insertFile(Files file);

    //修改一条文件记录
    void  updateFile(@Param("id") Integer id, @Param("file") Files file);

    //删除一条文件记录
    int  deleteFile(Integer id);


}
