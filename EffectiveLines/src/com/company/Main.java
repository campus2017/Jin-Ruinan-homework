package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


//abc
public class Main {
    public static void main(String[] args) {
	 // write your code here
        System.out.println("请输入文件路径：");
        Scanner s = new Scanner(System.in);
        String path = s.next();
        String[] pStrs = path.split("\\.");
        if(!pStrs[pStrs.length-1].equals("java")){
            System.out.println("不是java文件");
            return;
        }
        File file = new File(path);
        int validRowCnt = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = null;
            // 一次读入一行，直到读入null为文件结束
            while ((str = reader.readLine()) != null) {
                if(str.length() == 0){
                    continue;
                }else{
                    for(int i=0;i<str.length();i++){
                        char ch = str.charAt(i);
                        if(ch == ' ' || ch == '\t'){
                            continue;
                        }else if(ch == 47){//47即‘/’
                            if(i+1<str.length() && str.charAt(i+1) == 47){
                                break;
                            }else{
                                validRowCnt++;
                                break;
                            }
                        }else{
                            validRowCnt++;
                            int a = 9
                                    /3;
                            break;

                        }
                    }
                }
            }
            reader.close();
        }catch(Exception e){
            System.out.println("文件路径错误");
            return;
        }
        System.out.println("文件有效行数是"+validRowCnt+"行。");
    }
}
//C:\Users\e440\IdeaProjects\EffectiveLines\src\com\company\Main.java