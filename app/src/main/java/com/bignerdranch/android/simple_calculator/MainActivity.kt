package com.bignerdranch.android.simple_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.ArithmeticException
import com.bignerdranch.android.simple_calculator.databinding.ActivityMainBinding

//Resoureces used was medium blog post to understand how to create the calculator and
//used AI to help me fix any bugs I had along the way
class MainActivity : ComponentActivity() {

    // Declare the TextView for input display and binding for layout
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultTextView: TextView
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        resultTextView = findViewById(R.id.resultTextView)
    }


    // Function to handle clear button click
    fun onClear(view: View) {
        editText1.text.clear()
        editText2.text.clear()
        resultTextView.text = ""
    }


    // Function to handle operator button clicks
    fun onOperator(view: View) {
        operator = (view as Button).text.toString()
        val value1 = editText1.text.toString()
        val value2 = editText2.text.toString()

        if (value1.isNotEmpty() && value2.isNotEmpty()) {
            try {
                val result = calculateResult(value1.toDouble(), operator ,value2.toDouble())
                resultTextView.text = result.toString()
            } catch (e: NumberFormatException) {
                resultTextView.text = "Invalid Input"
            } catch (e: ArithmeticException) {
                resultTextView.text = "Error: ${e.message}"
            }
        } else {
            resultTextView.text = "Enter values in both fields"
        }
    }


    // Function to handle equal button click and perform calculations
    fun onEqual(view: View) {
        val value1 = editText1.text.toString()
        val value2 = editText2.text.toString()

        if (value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()) {
            try {
                val result = calculateResult(value1.toDouble(), operator ,value2.toDouble())
                resultTextView.text = result.toString()
            } catch (e: NumberFormatException) {
                // Handle invalid input format
                resultTextView.text = "Invalid Input"
            } catch (e: ArithmeticException) {
                // Handle arithmetic exceptions
                resultTextView.text = "Error: ${e.message}"
            } catch (e: IllegalArgumentException) {
                // Handle invalid operator
                resultTextView.text = "Error: ${e.message}"
            }
        } else {
            // Handle empty input fields
            resultTextView.text = "Enter values in both fields"
        }
    }

    private fun calculateResult(value1: Double, operator: String ,value2: Double): Double {
        return when (operator) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "*" -> value1 * value2
            "÷" -> {
                if (value2 != 0.0) {
                    value1 / value2
                } else {
                    throw ArithmeticException("Divide by Zero not allowed.")
                }
            }
            "%" -> {
                if (value2 != 0.0) {
                    value1 % value2
                } else {
                    throw ArithmeticException("Modulus by Zero not allowed.")
                }
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}





