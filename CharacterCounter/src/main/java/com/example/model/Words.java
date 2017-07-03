package com.example.model;

import java.io.Serializable;

/**
 * Created by e440 on 2017/7/1.
 */
public class Words implements Serializable {
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getEngCnt() {
        return engCnt;
    }

    public void setEngCnt(int engCnt) {
        this.engCnt = engCnt;
    }

    public int getNumCnt() {
        return numCnt;
    }

    public void setNumCnt(int numCnt) {
        this.numCnt = numCnt;
    }

    public int getChsCnt() {
        return chsCnt;
    }

    public void setChsCnt(int chsCnt) {
        this.chsCnt = chsCnt;
    }

    public int getPunCnt() {
        return punCnt;
    }

    public void setPunCnt(int punCnt) {
        this.punCnt = punCnt;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public String getThirdWord() {
        return thirdWord;
    }

    public void setThirdWord(String thirdWord) {
        this.thirdWord = thirdWord;
    }

    public int getFirstCnt() {
        return firstCnt;
    }

    public void setFirstCnt(int firstCnt) {
        this.firstCnt = firstCnt;
    }

    public int getSecondCnt() {
        return secondCnt;
    }

    public void setSecondCnt(int secondCnt) {
        this.secondCnt = secondCnt;
    }

    public int getThirdCnt() {
        return thirdCnt;
    }

    public void setThirdCnt(int thirdCnt) {
        this.thirdCnt = thirdCnt;
    }
    public String str;
    public int engCnt = 0;
    public int numCnt = 0;
    public int chsCnt = 0;
    public int punCnt = 0;
    public String firstWord;
    public String secondWord;
    public String thirdWord;
    public int firstCnt = 0;
    public int secondCnt = 0;
    public int thirdCnt = 0;
    public String toString(){
        return ""+engCnt+numCnt+chsCnt+punCnt+firstWord+secondWord+thirdWord+firstCnt+secondCnt+thirdCnt;
    }
    public static boolean isChineseWord(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        }
        return false;
    }

    public static boolean isChinesePunctuation(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(char c){
        if(c <= 0x0039 && c >= 0x0030){
            return true;
        }
        return false;
    }
    public static boolean isEnglishWord(char c){
        if((c >= 0x0041 && c <= 0x005A) || (c >= 0x0061 && c <= 0x007A)){
            return true;
        }
        return false;
    }

    public static boolean isEnglishPunctuation(char c){
        if((c >= 0x0020 && c <= 0x002F) || (c >= 0x003A && c <= 0x0040) || (c >= 0x005B && c <= 0x0060) || (c >= 0x007B && c <= 0x007E) ){
            return true;
        }
        return false;
    }




}
