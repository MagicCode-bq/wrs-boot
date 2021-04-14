package org.lbq.wrsboot.controller.file;

import org.lbq.wrsboot.Vo.FileVo;
import org.lbq.wrsboot.Vo.RespVo;
import org.lbq.wrsboot.bean.Files;
import org.lbq.wrsboot.service.impl.FileServiceImpl;
import org.lbq.wrsboot.utils.Base64ToMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RequestMapping("/file")
@RestController
public class UploadController {
    private  static final Logger Log = LoggerFactory.getLogger("File");

    @Autowired
    FileServiceImpl fileServiceImpl;



   @PostMapping("/upload")
    public RespVo fileLoad(@RequestBody FileVo fileVo) throws IOException {

       //使用工具类把Base64转换为multipartFile
       String shardBase64 = fileVo.getShard();
       MultipartFile multipartFile = Base64ToMultipartFile.base64ToMultipart(shardBase64);

       String userDir = System.getProperty("user.dir"); //当前项目根路径
       String  fullpath = new StringBuffer(userDir)
               .append("/src/main/resources/static/")
               .append(fileVo.getKey())
               .append(".")
               .append(fileVo.getSuffix())
               .append(".")
               .append(fileVo.getShardIndex())
                .toString();

       File dest = new File(fullpath); //创建文件路径
       multipartFile.transferTo(dest); //在文件路径中放入路径

      //存储上传文件的信息
       Files files = new Files();
       files.setName(fileVo.getName()); //文件的名称
       files.setPath(fileVo.getKey()+"."+fileVo.getSuffix()+"."+fileVo.getShardIndex()); //相对路径
       files.setSuffix(fileVo.getSuffix()); //后缀
       files.setSize(fileVo.getSize());// 文件大小
       files.setUser(fileVo.getUser()); //当前上传文件的用户
       files.setFilekey(fileVo.getKey());//文件标识
       files.setShardsize(fileVo.getShardSize());//片段大小
       files.setShardtotal(fileVo.getShardTotal());//上传总片段
       files.setShardindex(fileVo.getShardIndex());//片段索引
       files.setFilekey(fileVo.getKey());
       //获取当前系统时间
       long l = System.currentTimeMillis();
       files.setCreatetime(Long.toString(l));
       fileServiceImpl.insertFile(files);
       if(fileVo.getShardIndex()==fileVo.getShardTotal()){
                mergeFile(fileVo);
           return  new RespVo(200,"视频上传成功","http://localhost:8081/f/"+fileVo.getKey()+"."+fileVo.getSuffix());
       }
       return  new RespVo(204,"分片上传成功",fileVo.getShardIndex());
    }


    public void mergeFile(FileVo fileVo) throws FileNotFoundException {
      //1、文件存放的地址，生成文件对象
       String userDir = System.getProperty("user.dir"); //当前项目根路径
       //2、读取的文件路径
        String  fullpath = new StringBuffer(userDir)
                .append("/src/main/resources/static/")
                .toString();

       File file = new File(fullpath+fileVo.getKey()+"."+fileVo.getSuffix());
       //创建文件输出流,合并文件输出的位置，开启文件追加。
       FileOutputStream outputStream = new FileOutputStream(file,true);
       //创建文件输入流，读取存放的分片文件
       FileInputStream fileInputStream=null;
       //创建缓冲池
       byte[] byt = new byte[10*1024*1024];
       //判断文件是否读取完成
       int  let;

       try {  //开启输出流的文件追加，两次输出会合并
           for (int i = 1; i <= fileVo.getShardTotal(); i++) {
               fileInputStream =  new  FileInputStream(new File(fullpath+fileVo.getKey()+"."+fileVo.getSuffix()+"."+fileVo.getShardIndex()));
               while ((let=fileInputStream.read(byt))!=-1){
                   outputStream.write(byt,0,let); //读多少写多少 从数组0开始，到let结束
               }
           }
       } catch (IOException e) {
          Log.error("分片合并异常",e);
       }finally {
            try{
                    fileInputStream.close();
            }catch (Exception e){
                Log.error("关闭失败",e);
            }finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
       }

           //关闭流在栈中索引
           System.gc();
            try {
            Thread.sleep(100);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }

        Log.info("删除分片文件开始");
           for (int i = 1; i <=fileVo.getShardTotal();  i++) {
               File deleteFile = new File(fullpath + fileVo.getKey() + "." + fileVo.getSuffix() + "." + i);
               boolean result = deleteFile.delete();
                Log.info("删除{}",result?"成功":"失败");
           }

    }
}
