package com.example.kotlin_calculator

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

class Util {

    companion object {

        /**
         * 对0进行处理
         * https://www.iteye.com/topic/1117932?page=3
         */
        fun trimZeroOfNumber(value: Any): String {
            val temp: Any = BigDecimal(value.toString())
            val fmt: NumberFormat = NumberFormat.getInstance()
            fmt.setMaximumFractionDigits(100)
            fmt.setGroupingUsed(false)
            return fmt.format(temp)
        }

        /**
         * 去掉小数部分尾部的0
         * @param str
         * @return
         */
        fun deleteTailZero(str: String): String {
            var str = str
            if (str.indexOf(".") > 0) {
                //删掉尾数为0的字符
                str = str.replace("0+?$".toRegex(), "")
                //结尾如果是小数点，则去掉
                str = str.replace("[.]$".toRegex(), "")
            }
            return str
        }

        /**
         * 删除前置的0
         */
         fun deleteHeadZero(s: String): String? {
//        String newStr = s.replaceAll("^(0+)", "");
            return s.replace("^0+".toRegex(), "")
        }

        /**
         * 判断是否为小数
         */
        fun judgeIsDecimal(num: String): Boolean {
            var isdecimal = false
            if (num.contains(".")) {
                isdecimal = true
            }
            return isdecimal
        }

        /**
         *判断是否为运算符号
         */
        @Throws(PatternSyntaxException::class)
        fun isChinaPhoneLegal(str: String?): Boolean {
            val regExp = "[+×÷-]"
            val p: Pattern = Pattern.compile(regExp)
            val m: Matcher = p.matcher(str)
            return m.matches()
        }
    }

}