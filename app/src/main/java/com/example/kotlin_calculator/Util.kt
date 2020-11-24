package com.example.kotlin_calculator

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Util {

    companion object {

        /**
         * 对0进行处理
         * https://www.iteye.com/topic/1117932?page=3
         */
        fun trimZeroOfNumber(value: Any): String {
            val temp: Any = BigDecimal(value.toString())
            val fmt: NumberFormat = NumberFormat.getInstance()
            fmt.maximumFractionDigits = 100
            fmt.isGroupingUsed = false
            return fmt.format(temp)
        }

        /**
         * 去掉小数部分尾部的0
         * @param str
         * @return
         */
        fun deleteDecimalTailZero(str: String): String {
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
            return s.replace("0^0+".toRegex(), "")
        }

        /**
         * 判断是否为小数
         * Is it a decimal
         */
        fun judgeIsDecimal(num: String): Boolean {
            var isdecimal = false
            if (num.contains(".")) {
                isdecimal = true
            }
            return isdecimal
        }

        /**
         *判断是否为符号
         */
        fun isOperationSymbol(str: String?): Boolean {
            return regular("[+×÷.-]", str)
        }


        private fun regular(regExp: String?, str: String?): Boolean {
            val p: Pattern = Pattern.compile(regExp)
            val m: Matcher = p.matcher(str)
            return m.matches()
        }


        /**
         * 判断算式是否为空
         */
        fun isFormulaNotEmpty(formula: String): Boolean {
            if (formula.isEmpty()) {
                return false
            }
            return true
        }


        /**
         *防止「+-*÷()」0的情况
         */
        fun isSymbolZero(formula: String): Boolean {
            return formula[formula.length - 2] == '0' && formula[formula.length - 3].toString() == "."
        }

        /**
         * 检查（）是否配对
         */
        fun isBracketMatching(checkedStr: String): Boolean {
            val bracketsStack: Stack<Char> = Stack()
            val checkedCharArray = checkedStr.toCharArray()
            for (i in checkedCharArray.indices) {
                val c = checkedCharArray[i]

                // 左括号都压入栈顶，右括号进行比对
                if (c == '(') {
                    bracketsStack.push(c)
                } else if (c == ')') {
                    // 栈非空校验，防止首先出现的是右括号
                    if (bracketsStack.isEmpty()) {
                        return false
                    }
                    val popChar: Char = bracketsStack.pop()
                    if ('(' != popChar) {
                        return false
                    }
                }
            }

            // 结束后栈内为空则正常，否则返回字符串全长+1，表示有括号未结束
            return bracketsStack.isEmpty()
        }

        /**
         * 左括号左边是运算符号，右边是数字 或 在第一个
         * 右括号左边是数字，右边是运算符号 或 在最后一个
         */
//        fun checkBracket(formula: String){
//            if ()
//        }



        /**
         * 验证四则运算表达式的正则表达式（待完善）
         */
        fun checkExpression(expression: String): Boolean {
            //整数、小数
            val baseExp = "([0-9]*(\\.[0-9]*)?)"
            val integerDecimal =
                "((−${baseExp})|${baseExp}|(-${baseExp})(${baseExp}|([\\+\\-\\÷\\×]${baseExp})))+" //
            return regular(integerDecimal, expression)
        }
    }

}