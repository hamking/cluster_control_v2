package com.adb.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    /**
     * 读取文件
     * @param filePath 文件昵称
     * @return return
     */
    public String readFile(String filePath){

        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {

            String line = "";
            String allLine = "";
            while ((line = br.readLine()) != null) {

                allLine += (line+"\n");
            }
            return allLine;
        } catch (IOException e) {
        }
        return "";
    }

    /**
     * 保存文件
     * @param filePath 文件昵称
     * @param content 文件内容
     */
    public void saveFile(String content,String filePath) {

        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath, true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到map中
     * @return return
     */
    public Map<String,String> getAllFile(String directoryPath, boolean isAddDirectory) {

        Map<String,String> map = new HashMap<String, String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return map;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    map.put(file.getAbsolutePath(), file.getName());
                }
                map.putAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                map.put(file.getAbsolutePath(), file.getName());
            }
        }
        return map;
    }

    /**
     * 创建文件夹
     * @param path path
     * @return return
     * @throws Exception Exception
     */
    public boolean createDir(String path){
        File file = new File(path);
        if (file.exists()){
            return false;
        }
        if (file.mkdirs()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除文件夹
     * @param path path
     */
    public void deleteDir(String path){
        File file = new File(path);
        file.delete();
    }

    /**
     * copy文件
     * @param source source
     * @param dest dest
     * @throws IOException IOException
     */
    public void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }
}
