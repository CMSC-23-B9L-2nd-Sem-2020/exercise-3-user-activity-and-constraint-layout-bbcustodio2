package com.example.custodio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var nickname_input : TextView
    lateinit var nickname : TextView
    lateinit var change_button : Button
    lateinit var retry_button : Button
    lateinit var click_count : TextView
    lateinit var you_win : TextView
    lateinit var gridBox : ConstraintLayout
    private var flagCount = 0
    private var clickCount = 0
    val grid: List<Int> = listOf(
        10,14,14,14,6,
        11,15,15,15,7,
        11,15,15,15,7,
        11,15,15,15,7,
        9,13,13,13,5
    )

    var gridColor = arrayOf<Int>(
        1,1,1,1,1,
        1,1,1,1,1,
        1,1,1,1,1,
        1,1,1,1,1,
        1,1,1,1,1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nickname_input = findViewById(R.id.nickname_input)
        nickname = findViewById(R.id.nickname)
        change_button = findViewById(R.id.change_nickname)
        retry_button = findViewById(R.id.retry)
        you_win = findViewById(R.id.you_win)
        gridBox = findViewById(R.id.gridBox)

        nickname.setOnClickListener {
            clickHandlerFunction()
        }

        change_button.setOnClickListener {
            clickTextFunction(it)
        }

        retry_button.setOnClickListener{
            resetBoard()
        }
        setListeners()
    }
    private fun increaseBlack(){
        flagCount++
    }
    private fun decreaseBlack(){
        flagCount--
    }

    private fun resetBoard(){
        for(i in (0..24)){
            gridColor[i] = 1
            findViewById<TextView>(getId()[i]).setBackgroundResource(R.color.defaultColor)
        }
        you_win.visibility = View.GONE
        retry_button.visibility = View.GONE
        gridBox.visibility = View.VISIBLE
    }

    private fun checkWinCondition(){
        if(flagCount == 25){
            you_win.visibility = View.VISIBLE
            retry_button.visibility = View.VISIBLE
            gridBox.visibility = View.GONE
        }
    }

    private fun clickHandlerFunction() {
        nickname_input.visibility = View.VISIBLE
        change_button.visibility = View.VISIBLE
        nickname.visibility = View.GONE

        nickname_input.requestFocus()
    }

    private fun clickTextFunction(view: View){
        nickname_input.visibility = View.GONE
        change_button.visibility = View.GONE
        nickname.visibility = View.VISIBLE

        val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        nickname.text = nickname_input.text
    }

    private fun getId(): List<Int> {
        val idList: List<Int> = listOf(
            R.id.box_1,
            R.id.box_2,
            R.id.box_3,
            R.id.box_4,
            R.id.box_5,
            R.id.box_6,
            R.id.box_7,
            R.id.box_8,
            R.id.box_9,
            R.id.box_10,
            R.id.box_11,
            R.id.box_12,
            R.id.box_13,
            R.id.box_14,
            R.id.box_15,
            R.id.box_16,
            R.id.box_17,
            R.id.box_18,
            R.id.box_19,
            R.id.box_20,
            R.id.box_21,
            R.id.box_22,
            R.id.box_23,
            R.id.box_24,
            R.id.box_25
        )


        return idList

    }

    private fun setListeners(){
        for(item in getId()){
            findViewById<TextView>(item).setOnClickListener {
                invokeColors(it)
            }
        }
    }

    private fun invokeColors(view: View){
      var count = 0
        for(item in getId()){
            if (view.id == item) {
                changeColors(count, grid[count] )
            }
            count++
        }

    }

    private fun changeColors(count: Int, sides: Int){
        click_count = findViewById(R.id.clickCount)
        val clickPlaceholder = "No. of Clicks: "
        flipColor(count)
        //upper adjacent box
        if(sides and 1 == 1){
            flipColor((count-5))
        }
        if(sides and 2 == 2){
            flipColor((count+5))
        }
        if(sides and 4 == 4){
            flipColor((count-1))
        }
        if(sides and 8 == 8){
            flipColor((count+1))
        }

        click_count.text = clickPlaceholder.plus(++clickCount)
        checkWinCondition()
    }

    private fun flipColor(flip: Int){
        val box = findViewById<TextView>(getId()[flip])

        if(gridColor[flip] == 0){
            gridColor[flip] = 1
            decreaseBlack()
            box.setBackgroundResource(R.color.defaultColor)
        }
        else{
            gridColor[flip] = 0
            increaseBlack()
            box.setBackgroundResource(R.color.black)
        }
    }



}



