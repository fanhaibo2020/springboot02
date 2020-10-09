package com.example.mvcConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * mp4切割成m3u8和ts片段，mp4源文件和切片后的输出文件可以放在不同位置的改进版
 * 程序运行前提是要先安装ffmpeg
 * @Author fhb
 * @Date 2020/2/29 10:59
 * @Version 1.0
 **/
public class FfmpegUtils02 {


    public static void main(String[] args) throws Exception {
        String fileName="考场";   //不带文件后缀
//        String ffmpegExePath = "C:\\ffmpeg\\bin\\ffmpeg.exe";
        String inputFilePath = "D:\\testWEC\\mv\\考场1"; //源文件目录
        String outputFilePath = "D:\\testWEC\\mv\\考场2";
        File targetFile = new File(outputFilePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();  //创建要输出的目标目录
        }
        mp4ToM3u8(fileName,inputFilePath,outputFilePath);
    }

    /**
     * mp4格式 转化成 m3u8
     * @param fileName 视频名称（不带后缀）
     * @param inputFilePath 视频文件夹路径
     * @param outputFilePath 输出路径
     * @return
     * @throws Exception
     */
    public static Boolean mp4ToM3u8(String fileName,String inputFilePath,String outputFilePath) throws Exception {
        List<String> command_mp4_ts = buildCommand_mp4_ts(fileName, inputFilePath, outputFilePath);
        Boolean execute = execute(command_mp4_ts);
        if (execute){
            List<String> command_cutTs = buildCommand_cutTs(fileName, inputFilePath, outputFilePath);
            return execute(command_cutTs);
        }else {
            return false;
//            throw new Exception("c.mp4转换c.ts失败");
        }

    }

    /**
     *  构建mp4转换成ts命令
     * @param fileName 文件名称
     * @param inputFilePath mp4视频路径
     * @param outputFilePath 生成的ts视频路径
     */
    public static List<String> buildCommand_mp4_ts(String fileName,String inputFilePath,String outputFilePath){
        List<String> command = new ArrayList<String>();
//        command.add("ffmpeg");
        command.add("C:\\Users\\fanha\\Desktop\\ffmpeg-4.2.2-win64-shared\\bin\\ffmpeg.exe");
        command.add("-y");
        command.add("-i");
        command.add(inputFilePath+ File.separator+fileName+".mp4");
        command.add("-vcodec");
        command.add("copy");
        command.add("-acodec");
        command.add("copy");
        command.add("-vbsf");
        command.add("h264_mp4toannexb");
        command.add(outputFilePath+ File.separator+fileName+".ts");
        return command;
    }

    /**
     * 构建ts视频切片命令 生成m3u8
     * @param fileName 文件名称
     * @param inputFilePath mp4视频路径
     * @param outputFilePath 生成的ts视频路径
     * @return
     */
    public static List<String> buildCommand_cutTs(String fileName,String inputFilePath,String outputFilePath){
        List<String> command = new ArrayList<String>();
//        command.add("ffmpeg");
        command.add("C:\\Users\\fanha\\Desktop\\ffmpeg-4.2.2-win64-shared\\bin\\ffmpeg.exe");
        command.add("-i");
        command.add(outputFilePath+ File.separator+fileName+".ts");
        command.add("-c");
        command.add("copy");
        command.add("-map");
        command.add("0");
        command.add("-f");
        command.add("segment");
        command.add("-segment_list");
        command.add(outputFilePath+ File.separator+fileName+".m3u8");
        command.add("-segment_time");
        command.add("5");
        command.add(outputFilePath+ File.separator+fileName+"-%03d.ts");
        return command;
    }

    /**
     * ffmpeg执行命令
     * @param command 命令
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static  Boolean execute(List<String> command) throws IOException, InterruptedException {
        System.out.println(command.toString());
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        //正常信息和错误信息合并输出
        builder.redirectErrorStream(true);
        BufferedReader br = null;
        try {
            //开始执行命令
            Process process = builder.start();
            //如果你想获取到执行完后的信息，那么下面的代码也是需要的
            StringBuffer sbf = new StringBuffer();
            String line = null;
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(" ");
            }
            String resultInfo = sbf.toString();
            System.out.println(resultInfo);
            //等待命令子线程执行完成
            int exitValue = process.waitFor();
            process.destroy();
//            //完成后执行回调
            if (exitValue == 0) {
                System.out.printf("执行成功");
                return Boolean.TRUE;
            }else {
                System.out.printf("执行失败");
                return Boolean.FALSE;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new InterruptedException("线程阻塞异常:" + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
