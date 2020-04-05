package com.e.resincalculator;

public class EnglishNumberToPersian {

    public String convert(String text){

        String matn;
        matn = text.replace("0","۰")
                   .replace("1","١")
                   .replace("2","٢")
                   .replace("3","٣")
                   .replace("4","۴")
                   .replace("5","۵")
                   .replace("6","۶")
                   .replace("7","۷")
                   .replace("8","۸")
                   .replace("9","۹")
                   .replace("10","۱۰");
        return matn;
    }


    public String convertToEnglish(String text){

        String matn;
        matn = text.replace("۰","0")
                .replace("۱","1")
                .replace("۲","2")
                .replace("۳","3")
                .replace("۴","4")
                .replace("۵","5")
                .replace("۶","6")
                .replace("۷","7")
                .replace("۸","8")
                .replace("۹","9")
                .replace("۱۰","10");
        return matn;
    }

}
