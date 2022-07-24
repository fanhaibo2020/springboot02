package com.example.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @descriable 正则匹配url并下载文件
 * @Author fhb
 * @Date 2021/6/20 11:21
 * @Version 1.0
 **/
public class UrlUtils {

    public static void main(String[] args) throws Exception {
//        String content = "这是内容啊，<img src=\"http=10.20.17.53:81/news/图片1.png\" data-id=\"null\" data-unknow=\"true\">图片1的名称</img>，在随便写点什么东西啊。" +
//                "在写一点吧。：<img src=\"http=10.20.17.53:81/news/图片2.jpg\" data-id=\"null\" data-unknow=\"undefined\">图片2的名称</img>";
        String content = "这是内容啊，<img src=\"https://nimg.ws.126.net/?url=http%3A%2F%2Fcms-bucket.ws.126.net%2F2021%2F0620%2Fa0a4f9e9j00qv04ij009pc000jg00pxc.jpg&thumbnail=660x2147483647&quality=80&type=jpg\" data-id=\"null\" data-unknow=\"true\">图片1的名称</img>，在随便写点什么东西啊。" +
                "在写一点吧。：<img src=\"http=10.20.17.53:81/news/图片2.jpg\" data-id=\"null\" data-unknow=\"undefined\">图片2的名称</img>";
        List<String> list = getUrlFromContent(content);
        System.out.println("正则匹配内容list="+list);
        System.out.println("list.size()="+list.size());
//        try{
            downloadFromUrl(list);
//        }catch (Exception e){
//            System.out.println("下载异常="+e.getMessage());
//        }
        System.out.println("执行结束");
    }

    //正则匹配出符合要求的url,并下载相关的文件
    public static List<String> getUrlFromContent(String content) throws Exception{
        List<String> list = new ArrayList<String>();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>  <img alt="" src="1.jpg"></img>  <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
//        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Pattern p_img = Pattern.compile("<(img|IMG)(.*? data-unknow=\"true\")(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);
                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }

    //执行下载、写库的动作
    public static void downloadFromUrl(List<String> list) throws Exception{
        String descPath = "D:\\testWEC"; //文件在服务器上的保存地址
        for(String str:list){
            System.out.println("str="+str);
            String savePath = str.substring(str.lastIndexOf("/"));
            System.out.println("savePath="+savePath); //   /图片1.png
            String fileName = str.substring(str.lastIndexOf("/")+1);
            System.out.println("fileName="+fileName);  //  图片1.png
            String fileType = str.substring(str.lastIndexOf(".")+1);
            System.out.println("fileType="+fileType); //png
            downloadFile(str,fileName,descPath);
        }
    }

    //下载文件
    public static void downloadFile(String urlStr,String fileName,String savePath) throws Exception{
//        // 创建一个URL链接
//        URL url = new URL(src);
//        // 获取连接
//        URLConnection conn = url.openConnection();
//        // 获取文件全路径
//        String fileName = url.getFile();
//        // 获取文件名
//        fileName = fileName.substring(fileName.lastIndexOf("/"));

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }

        fileName = "图一.png"; //因截取的fileName不规则,故先这样代替;

        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        System.out.println("info:"+url+" download success");
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
