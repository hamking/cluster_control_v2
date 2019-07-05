package com.zciteam.util;

import com.zciteam.enums.DirectoryEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化所有目录
 */
public class Directory{


    //初始化服务器已知目录
    public void init() {
        //创建默认目录
        createDir(DirectoryEnum.APPLICATION.getStartInfo());
        createDir(DirectoryEnum.IMAGE.getStartInfo());
        createDir(DirectoryEnum.VIDEO.getStartInfo());
        createDir(DirectoryEnum.ADDRESS_BOOK.getStartInfo());
        createDir(DirectoryEnum.BOOK.getStartInfo());
        createDir(DirectoryEnum.OYHER.getStartInfo());
    }

    /**
     * 读取文件
     * @param fileName 文件昵称
     * @return return
     */
    public static String readFile(DirectoryEnum type, String fileName){

        String filePath = type.getStartInfo() + fileName;
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
     * @param type FileType
     * @param file MultipartFile
     * @return boolean
     */
    public static String saveFile(DirectoryEnum type, MultipartFile file) {

        String filePath = type.getStartInfo () + file.getOriginalFilename();
        File desFile = new File (filePath);

        try {
            file.transferTo (desFile);
            return filePath;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace ();
            return null;
        }
    }

    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到map中
     * @return return
     */
    public static Map<String,String> getAllFile(String directoryPath, boolean isAddDirectory) {

        Map<String,String> map = new HashMap<String, String> ();
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
    private static boolean createDir(String path){
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
}

