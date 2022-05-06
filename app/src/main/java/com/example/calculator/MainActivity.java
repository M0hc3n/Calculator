package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText inputBoard;
    private TextView resultBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBoard= findViewById(R.id.View);
        resultBoard=findViewById(R.id.resultView);
        inputBoard.setShowSoftInputOnFocus(false);

        inputBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getText(R.string.display).equals(inputBoard.getText().toString())) {
                    inputBoard.setText("");
                }
            }
        });

        resultBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getString(R.string.resultDisplay).equals(resultBoard.getText().toString())){
                    inputBoard.setText(resultBoard.getText().toString());
                    inputBoard.setSelection(resultBoard.getText().length());
                }
            }
        });

    }

    public void updateText(String newStr){
        String oldSTR=inputBoard.getText().toString();
        int cursorPos=inputBoard.getSelectionStart();

        if(getString(R.string.display).equals(inputBoard.getText().toString())){
            inputBoard.setText(newStr);
            inputBoard.setSelection(cursorPos+1);
        }
        else{
            inputBoard.setText( String.format("%s%s%s", oldSTR.substring(0,cursorPos) , newStr , oldSTR.substring(cursorPos)));
            inputBoard.setSelection(cursorPos+1);

        }
    }

    public void zeroBtn(View v){
        updateText("0");
    }

    public void oneBtn(View v){
        updateText("1");

    }
    public void twoBtn(View v){
        updateText("2");

    }

    public void threeBtn(View v){
        updateText("3");

    }

    public void fourBtn(View v){
        updateText("4");

    }

    public void fiveBtn(View v){
        updateText("5");

    }

    public void sixBtn(View v){
        updateText("6");

    }

    public void sevenBtn(View v){
        updateText("7");

    }

    public void eightBtn(View v){
        updateText("8");

    }

    public void nineBtn(View v){
        updateText("9");

    }

    public void dotBtn(View v){
        updateText(".");

    }

    public void clearBtn(View v){
        inputBoard.setText("");
    }

    public void multiplyBtn(View v){
        updateText("*");

    }

    public void addBtn(View v){
        updateText("+");

    }

    public void plusMinusBtn(View v){
        updateText("-");

    }

    public void divideBtn(View v){
        updateText("/");

    }

    public void subtractBtn(View v){
        updateText("-");

    }

    public void parenthesisBtn(View v){
       int cursorPos=inputBoard.getSelectionStart();
       int openPar=0;
       int closedPar=0;
       int size=inputBoard.getText().length();


       for(int i=0; i<cursorPos; i++){

           if(inputBoard.getText().toString().charAt(i) == '('){
               openPar++;
           }
           else if(inputBoard.getText().toString().charAt(i) == ')'){
               closedPar++;
           }
       }

       if(openPar==closedPar || inputBoard.getText().toString().charAt(size - 1) == '('){
           updateText("(");
       }
       else if(closedPar<openPar && inputBoard.getText().toString().charAt(size - 1) != '('){
           updateText(")");
       }

       inputBoard.setSelection(cursorPos+1);
    }

    public void powerBtn(View v){
        updateText("^");

    }

    public void clearLastBtn(View v){
        int cursorPos=inputBoard.getSelectionStart();
        int size=inputBoard.getText().length();

        if(cursorPos!=0 && size!=0){
            SpannableStringBuilder target= (SpannableStringBuilder) inputBoard.getText();
            target.replace(cursorPos-1, cursorPos, "");
            inputBoard.setText(target);
            inputBoard.setSelection(cursorPos-1);
        }
        else{
            Toast.makeText(this, "Please Enter a valid expression", Toast.LENGTH_SHORT).show();
        }
    }

    public void equalBtn(View v){
        String userExp=inputBoard.getText().toString();

        Expression exp=new Expression(userExp);

        String result=String.valueOf(exp.calculate());

        resultBoard.setText(result);

    }


}