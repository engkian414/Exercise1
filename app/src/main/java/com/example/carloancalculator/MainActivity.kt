package com.example.carloancalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener{
            calculate(it)
        }
    }

    private fun calculate(view: View){
        val carPrice=findViewById<EditText>(R.id.cPrice)
        val loanPeriod=findViewById<EditText>(R.id.loanPeriod)
        val downPayment=findViewById<EditText>(R.id.dpAmount)
        val interestRate=findViewById<EditText>(R.id.interest)

        val carLoan=(carPrice.text.toString().toBigDecimal()-downPayment.text.toString().toBigDecimal()).setScale(2,RoundingMode.HALF_EVEN)
        val intRate=BigDecimal(interestRate.text.toString().toDouble()/100).setScale(2,RoundingMode.HALF_EVEN)
        val interest=(carLoan*intRate*loanPeriod.text.toString().toBigDecimal()).setScale(2,RoundingMode.HALF_EVEN)
        val monthlyRapayment=(((carLoan+interest)/loanPeriod.text.toString().toBigDecimal()).setScale(2,RoundingMode.HALF_EVEN)/ BigDecimal(12)).setScale(2,RoundingMode.HALF_EVEN)

        val carLoanRes=findViewById<TextView>(R.id.carLoanRes)
        carLoanRes.text=carLoan.toString()
        //carLoanRes.text="hi"
        carLoanRes.visibility=View.VISIBLE
        val monthRepayRes=findViewById<TextView>(R.id.monthRepayRes)
        monthRepayRes.text=monthlyRapayment.toString()
        //monthRepayRes.text="hi"
        monthRepayRes.visibility=View.VISIBLE
        val interestRes=findViewById<TextView>(R.id.interestRes)
        interestRes.text=interest.toString()
        //interestRes.text="hi"
        interestRes.visibility=View.VISIBLE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
