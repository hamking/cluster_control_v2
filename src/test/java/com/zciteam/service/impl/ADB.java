package com.zciteam.service.impl;

import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class ADB {


    @Test
    public void test(){
//        System.out.println(findURootPath());

        File file = new File("/Volumes/aaa");
        File[] files = file.listFiles();
        System.out.println(files);
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getAbsolutePath());
        }
    }


    /**
     * 得到系统U盘根目录
     */
    public String findURootPath(){
        FileSystemView sys = FileSystemView.getFileSystemView();
        //循环盘符
        File[] files = File.listRoots();
        for(File file:files){
            //得到系统中存在的C:\,D:\,E:\,F:\,H:\
            System.out.println("系统中存在的: "+file.getPath());
        }
        File file = null;
        String path = null;
        for(int i = 0; i < files.length; i++) {
            //得到文字命名形式的盘符系统 (C:)、软件 (D:)、公司文档 (E:)、测试U盘 (H:)
            System.out.println("得到文字命名形式的盘符: "+sys.getSystemDisplayName(files[i]));
            if(sys.getSystemDisplayName(files[i]).contains("测试U盘")){
                file = files[i];
                break;
            }
        }
        if(file!=null){
            path = file.getPath();
        }
        return path;
    }
}
