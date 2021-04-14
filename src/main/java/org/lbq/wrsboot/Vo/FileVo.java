package org.lbq.wrsboot.Vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileVo {

     private String  shard; //片段
     private Integer shardSize;//片段大小
     private Integer  shardIndex; //片段索引
     private Integer  shardTotal; //片段总数
     private String  user; //上传的用户
     private String  name; //上传的文件名
     private String  suffix; //文件后缀
     private Integer  size; //文件大小
     private String  key; //文件标识


}
