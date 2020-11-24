package com.example.kotlin_calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_calculator.Calculate.exec
import com.example.kotlin_calculator.Util.Companion.checkExpression
import com.example.kotlin_calculator.Util.Companion.deleteDecimalTailZero
import com.example.kotlin_calculator.Util.Companion.deleteHeadZero
import com.example.kotlin_calculator.Util.Companion.isBracketMatching
import com.example.kotlin_calculator.Util.Companion.isOperationSymbol
import com.example.kotlin_calculator.Util.Companion.isSymbolZero
import com.example.kotlin_calculator.Util.Companion.judgeIsDecimal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //记录历史算式
    private var historyFormula = mutableListOf<String>()

    //是否重新输入算式(如果点击了"="则设置新的式子)
    private var reInput: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val historyItem:List<String> = listOf("1","123","23","123123","21323")
        recycler_view.adapter = HistoryAdapter(historyItem)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    //基本点击事件
    @SuppressLint("SetTextI18n")
    fun onClick(view: View) {
        var tempFormula = calculator_formula.text.toString()
        when (view) {

//          数字
            tv0 -> {
                //首位为0，则一直置为0
                if (tempFormula == "0") {
                    calculator_formula.text = "0"
                } else {
                    clickNumber("$tempFormula${0}")
                }
            }
            tv1 -> clickNumber("$tempFormula${1}")
            tv2 -> clickNumber("$tempFormula${2}")
            tv3 -> clickNumber("$tempFormula${3}")
            tv4 -> clickNumber("$tempFormula${4}")
            tv5 -> clickNumber("$tempFormula${5}")
            tv6 -> clickNumber("$tempFormula${6}")
            tv7 -> clickNumber("$tempFormula${7}")
            tv8 -> clickNumber("$tempFormula${8}")
            tv9 -> clickNumber("$tempFormula${9}")
//            符号
            dot, devide, multiplication, minus, add, left_parenthesis, right_parenthesis -> clickOperator(
                tempFormula,
                view
            )

//          等于，删除
            equal -> clickEqual(tempFormula)
            delete -> clickDelete(tempFormula)
//            清空
            clear -> clearScreen()
        }
    }


    /**
     * 返回计算结果
     */
    private fun calculate(formula: String): String {
        return if (formula.isNotEmpty()&& checkExpression(formula)) {
            exec(formula).toString()
        } else {
            "输入错误"
        }
    }

    /**
     * 点击数字
     */
    private fun clickNumber(formula: String) {
        var formula: String = formula

        //排除0开头的整数
        if (formula.first() == '0' && formula.length == 2 || (if (formula.length > 2) isSymbolZero(
                formula
            ) else false)
        ) {
            return
        }
        //如果点击了"="则设置新的算式
        if (reInput) {
            //获取最后一个字符
            formula = formula.last().toString()
            calculator_formula.text = ""
            calculator_result.text = "0"
            reInput = false
        }
        calculator_result.textSize = 36.0f
        calculator_formula.textSize = 36.0f
        setCalculatorFormula(formula)
        setResultText(calculate(formula))
    }

    /**
     * 点击『+-×÷().』
     */
    private fun clickOperator(formula: String, view: View) {

        //算式开头只能输入负数
        if (formula.isEmpty()) {
            when(view){
                minus -> setCalculatorFormula("$formula-")
            }
            return
        }

        //禁止连续两次输入符号
        if (isOperationSymbol(formula.last().toString())) {
            when (view) {

            }
        } else {
            when (view) {
                dot -> setCalculatorFormula("$formula.")
                devide -> setCalculatorFormula("$formula÷")
                multiplication -> setCalculatorFormula("$formula×")
                minus -> setCalculatorFormula("$formula-")
                add -> setCalculatorFormula("$formula+")
                left_parenthesis -> setCalculatorFormula("$formula(")
                right_parenthesis -> setCalculatorFormula("$formula)")
            }
        }
    }

    /**
     * 点击"="
     */
    private fun clickEqual(formula: String) {
        calculator_result.textSize = 40.0f
        calculator_formula.textSize = 30.0f
        historyFormula.add("$formula=${calculator_result.text}")//保存历史式子
        reInput = true
        setCalculatorFormula(formula)
        Log.i("点击=:", formula)
        Log.i("点击=11111:", calculate(formula))
        setResultText("=${calculate(formula)}")
    }

    /**
     * 点击删除
     */
    private fun clickDelete(formula: String) {
        var temp = ""

        println(formula)

        //如果没有字符了，则直接设置为空
        if (formula.length > 1) {
            setCalculatorFormula(formula.substring(0, formula.length - 1))
            ////重新获取删除后的式子,下面计算需要用到
            temp = calculator_formula.text.toString()
        } else {
            setCalculatorFormula("")
            setResultText("0")
            return
        }

        //最后一个字符是否含于「+-×÷.」。如果是则删除，然后再运算
        if (temp.length >= 2 && isOperationSymbol(temp[temp.length - 1].toString())) {
            setResultText(calculate(temp.substring(0, temp.length - 1)))
        } else {
            setResultText(calculate(temp))
        }
    }

    /**
     * 设置要显示计算结果
     */
    private fun setResultText(result: String) {
//        //设置结果text
        calculator_result.text = if (judgeIsDecimal(result)) {
            //去掉double尾部多余的0
            deleteDecimalTailZero(result)
        } else {
            //去掉整数头部多余的0
            deleteHeadZero(result).toString()
        }
    }

    /**
     * 设置要显示的运算式子
     */
    private fun setCalculatorFormula(formula: String) {
        calculator_formula.text = formula
    }


    /**
     * 清屏
     */
    private fun clearScreen() {
        calculator_formula.text = ""
        calculator_result.text = "0"
    }


}