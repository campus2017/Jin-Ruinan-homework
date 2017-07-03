package com.example.controller;

import com.example.model.Words;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Chingyu Mo
 * @create 2016-07-23-20:20
 */
// 注解标注此类为springmvc的controller，url映射为"/home"
@Controller
@RequestMapping("/home")
public class HomeController {
    //添加一个日志器
    private Map<String,Integer> map = new HashMap<String,Integer>();
    //映射一个action
    @RequestMapping("/receiveData")
    public  String ReceiveData(String textarea, Model model){
        map.clear();
        System.out.println(textarea);
        Words w = new Words();
        w.str = textarea;
        char[] chs = textarea.toCharArray();//abcdefghijklmn,.,.;;;，。，。，，。1234567啊啊地方撒但是发射点法发
        for(int i=0;i<chs.length;i++){
            char ch = chs[i];
            if(Words.isChinesePunctuation(ch) || Words.isEnglishPunctuation(ch)){
                w.punCnt++;
            }
            if(Words.isChineseWord(ch)){
                w.chsCnt++;
            }
            if(Words.isEnglishWord(ch)){
                w.engCnt++;
            }
            if(Words.isNumber(ch)){
                w.numCnt++;
            }
            ;
            String str = String.valueOf(ch);
            if(!map.containsKey(str)){
                map.put(str,1);
            }else{
                map.put(str,map.get(str)+1);
            }
        }
        List<Map.Entry<String,Integer>> list =
                new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        switch (list.size()){
            case 0: {
                break;
            }
            case 1:{
                w.firstWord = list.get(0).getKey();
                w.firstCnt = list.get(0).getValue();
                break;
            }
            case 2:{
                w.firstWord = list.get(0).getKey();
                w.firstCnt = list.get(0).getValue();
                w.secondWord = list.get(1).getKey();
                w.secondCnt = list.get(1).getValue();
                break;
            }
            default:{
                w.firstWord = list.get(0).getKey();
                w.firstCnt = list.get(0).getValue();
                w.secondWord = list.get(1).getKey();
                w.secondCnt = list.get(1).getValue();
                w.thirdWord = list.get(2).getKey();
                w.thirdCnt = list.get(2).getValue();
                break;
            }
        }
        System.out.println(""+w.punCnt+":"+w.chsCnt+":"+w.engCnt+":"+w.numCnt+":"+w.firstWord+":"+w.firstCnt+":"+w.secondWord
            +":"+w.secondCnt+":"+w.thirdWord+":"+w.thirdCnt);
        //aaabbcdddddee,,...，，。。。。。11234345645金金金金金
        model.addAttribute("words", w);

        return "index";
    }

    @RequestMapping("/clearData")
    public String ClearData(Model model){
        Words w = new Words();
        model.addAttribute("words", w);
        return "index";

    }
}