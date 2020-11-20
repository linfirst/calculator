package com.example.kotlin_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        var num = calculator.text

        if (num.equals("0")) {
            num = ""
        }

        when (view) {
            tv0 -> {
                if (num.equals("0")) {
                    calculator.setText("0")
                } else {
                    calculator.setText("$num${0}")
                }
            }
            tv1 -> calculator.setText("$num${1}")
            tv2 -> calculator.setText("$num${2}")
            tv3 -> calculator.setText("$num${3}")
            tv4 -> calculator.setText("$num${4}")
            tv5 -> calculator.setText("$num${5}")
            tv6 -> calculator.setText("$num${6}")
            tv7 -> calculator.setText("$num${7}")
            tv8 -> calculator.setText("$num${8}")
            tv9 -> calculator.setText("$num${9}")
            dot -> calculator.setText("$num.")
        }
    }

    /**
     * 清屏
     */
    fun clearScreen(view: View) {
        when (view) {
            clear -> calculator.setText("0")
        }
    }

    /**
     * 删除
     */
    fun deleteNumber(view: View) {
        var num = calculator.text
        when (view) {
            delete -> {
                if (num.length > 1) {
                    calculator.setText(num.substring(0, num.length - 1))
                } else {
                    calculator.setText("0")
                }
            }
        }
    }

    /**
     *
     */
    fun symbol(view: View) {
        var oldNumber = calculator.text
        when (view) {
            modulus -> calculator.setText("$oldNumber%")
            devide -> calculator.setText("$oldNumber÷")
            multiplication -> calculator.setText("$oldNumber*")
            minus -> calculator.setText("$oldNumber-")
            add -> calculator.setText("$oldNumber+")
        }
    }


}