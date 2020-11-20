package com.example.kotlin_calculator

import java.math.BigDecimal
import java.text.NumberFormat

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
         * 判断是否为小数
         */
        fun judgeIsDecimal(num: String): Boolean {
            var isdecimal = false
            if (num.contains(".")) {
                isdecimal = true
            }
            return isdecimal
        }
    }

}