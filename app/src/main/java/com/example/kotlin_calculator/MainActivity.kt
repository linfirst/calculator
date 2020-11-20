package com.example.kotlin_calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlin_calculator.Calculate.deleteZero
import com.example.kotlin_calculator.Calculate.exec
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var historyFormula=mutableListOf<String>()
    var reInput:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //基本点击事件
    fun onClick(view: View) {
        var temp = tv_formula.text as String

        //如果点击了"="则重新计算新的式子
        if (reInput){
            temp=""
            tv_formula.setText("")
            calculator_result.setText("0")
            reInput=false
        }

        when (view) {
            tv0 -> {
                if (temp.equals("0")) {
                    tv_formula.setText("0")
                } else {
                    tv_formula.setText("$temp${0}")
                }
            }
//            数字
            tv1 -> tv_formula.setText("$temp${1}")
            tv2 -> tv_formula.setText("$temp${2}")
            tv3 -> tv_formula.setText("$temp${3}")
            tv4 -> tv_formula.setText("$temp${4}")
            tv5 -> tv_formula.setText("$temp${5}")
            tv6 -> tv_formula.setText("$temp${6}")
            tv7 -> tv_formula.setText("$temp${7}")
            tv8 -> tv_formula.setText("$temp${8}")
            tv9 -> tv_formula.setText("$temp${9}")
            dot -> tv_formula.setText("$temp.")
//            符号
            devide -> tv_formula.setText("$temp÷")
            multiplication -> tv_formula.setText("$temp×")
            minus -> tv_formula.setText("$temp-")
            add -> tv_formula.setText("$temp+")
            left_parenthesis->tv_formula.setText("$temp(")
            right_parenthesis->tv_formula.setText("$temp)")
        }
    }

    /**
     * 计算结果 显示
     */
    fun result(view: View){
        reInput=true
        var formula:String=tv_formula.text.toString()//需计算的式子
        var result=exec(formula).toString()//计算结果
        result= deleteZero(result)//去掉double尾部多余的0
        historyFormula.add("$formula=$result")//保存历史式子
        when(view){
            equal->{
//                calculator.setText(exec("-8*(((-2+4)+3)/((-1-5)*-2)-5)").toString())
                Log.i(this.javaClass.name+"AAAAAAAAAA",formula)
                calculator_result.setText("=$result")
                calculator_result.setTextSize(43.0f)
                tv_formula.setTextSize(30.0f)
            }
        }
    }

    /**
     * 清屏
     */
    fun clearScreen(view: View) {
        when (view) {
            clear -> {
                tv_formula.setText("")
                calculator_result.setText("0")
            }
        }
    }

    /**
     * 删除
     */
    fun deleteNumber(view: View) {
        var num = tv_formula.text
        when (view) {
            delete -> {
                if (num.length > 1) {
                    tv_formula.setText(num.substring(0, num.length - 1))
                } else {
                    tv_formula.setText("0")
                }
            }
        }
    }
}