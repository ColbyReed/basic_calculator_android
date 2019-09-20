package com.mobile.calculator.controller;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.calculator.R;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorActivity extends AppCompatActivity {

    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero,
            btnAddition, btnSubtract, btnDivide, btnMultiply, btnEqual, btnClear, btnBackspace, btnPosNeg, btnDecimal;
    TextView txtCalculations;
    boolean isNeg;
    boolean isDecimal = false;
    boolean canAdd, canSub, canDiv, canMult;
    boolean canPush = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);

        btnAddition = findViewById(R.id.btnAddition);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnEqual = findViewById(R.id.btnEqual);

        btnClear = findViewById(R.id.btnClear);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnPosNeg = findViewById(R.id.btnPosNeg);
        btnDecimal = findViewById(R.id.btnDecimal);

        txtCalculations = findViewById(R.id.txtCalculations);
        txtCalculations.setMovementMethod(new ScrollingMovementMethod());
        txtCalculations.setText("");
        isNeg = false;

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clears calculation string.
                txtCalculations.setText("");
                isNeg = false;
                isDecimal = false;
                canPush = true;
                canAdd= false;
                canSub = false;
                canDiv = false;
                canMult = false;
            }
        });

        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canPush) {
                    String number = txtCalculations.getText().toString();
                    int l = txtCalculations.getText().toString().length();

                    //Deletes last character in string and says part of string is positive if open parenthesis is removed, and positive if vice versa.
                    if (number.length() > 0) {
                        if(txtCalculations.getText().toString().endsWith("(-")){
                            number = number.substring(0, number.length() - 1);
                            isNeg = false;
                        }
                        number = number.substring(0, number.length() - 1);
                        if (txtCalculations.getText().toString().charAt(l - 1) == '(') {
                            isNeg = false;
                        } else if (txtCalculations.getText().toString().charAt(l - 1) == ')') {
                            isNeg = true;
                        }
                        if (txtCalculations.getText().toString().charAt(l - 1) == '.') {
                            isDecimal = false;
                        }
                        if ((txtCalculations.getText().toString().charAt(l - 1) == '+' || txtCalculations.getText().toString().charAt(l - 1) == '-' || txtCalculations.getText().toString().charAt(l - 1) == '*' || txtCalculations.getText().toString().charAt(l - 1) == '/') && txtCalculations.getText().toString().charAt(l - 2) == ')') {
                            isDecimal = false;
                            isNeg = false;
                        }
                        //Enables decimal placement if operator is deleted.
                        if ((txtCalculations.getText().toString().charAt(l - 1) == '+' || txtCalculations.getText().toString().charAt(l - 1) == '-' || txtCalculations.getText().toString().charAt(l - 1) == '*' || txtCalculations.getText().toString().charAt(l - 1) == '/') && !txtCalculations.getText().toString().endsWith("(-")) {
                            isDecimal = false;
                            boolean switched = false;
                            for (int i = txtCalculations.getText().toString().length() - 1; i > 0 && !switched; i--) {
                                if (!txtCalculations.getText().toString().isEmpty() && txtCalculations.getText().toString().charAt(i) == '.'){
                                    isDecimal = true;
                                    switched = true;
                                }
                                if (!txtCalculations.getText().toString().isEmpty() && (txtCalculations.getText().toString().charAt(i-1) == '+' || txtCalculations.getText().toString().charAt(i-1) == '-' || txtCalculations.getText().toString().charAt(i-1) == '*' || txtCalculations.getText().toString().charAt(i-1) == '/')){
                                    isDecimal = false;
                                    switched = true;
                                }
                            }
                        }
                        canAdd = true;
                        canSub = true;
                        canDiv = true;
                        canMult = true;

                    }
                    txtCalculations.setText(number);
                    if(txtCalculations.getText().toString().isEmpty()){
                        canAdd = false;
                        canSub = false;
                        canDiv = false;
                        canMult = false;
                        isNeg = false;
                    }
                }else{
                    txtCalculations.setText("");
                    canAdd = false;
                    canSub = false;
                    canDiv = false;
                    canMult = false;
                }
            }
        });

        //Allows buttons 0 through 9 to insert respective numbers into calculation string
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "1");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("1");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "2");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("2");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "3");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("3");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "4");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("4");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "5");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("5");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "6");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("6");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "7");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("7");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "8");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("8");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "9");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("9");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canPush && !txtCalculations.getText().toString().endsWith(")")) {
                    txtCalculations.setText(txtCalculations.getText() + "0");
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                }else if(!canPush){
                    txtCalculations.setText("0");
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canPush  && !txtCalculations.getText().toString().endsWith(")")) {
                    if(!txtCalculations.getText().toString().isEmpty() && (txtCalculations.getText().toString().contains("+00") || txtCalculations.getText().toString().contains("-00") || txtCalculations.getText().toString().contains("/00") || txtCalculations.getText().toString().contains("*00") || txtCalculations.getText().toString().startsWith("00"))){
                        for (int i = txtCalculations.getText().toString().length() - 1; i > 0 && txtCalculations.getText().toString().length() > 1 && (txtCalculations.getText().toString().contains("+0") || txtCalculations.getText().toString().contains("-0") || txtCalculations.getText().toString().contains("/0") || txtCalculations.getText().toString().contains("*0") || txtCalculations.getText().toString().startsWith("0")); i--) {
                            if(txtCalculations.getText().toString().charAt(i - 1) == '0' && txtCalculations.getText().toString().charAt(i) != '.'){
                                StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                                sb.deleteCharAt(i - 1);
                                txtCalculations.setText(sb);
                            }
                        }
                    }
                    //If string is empty, will insert 0 and decimal
                    if (txtCalculations.getText().toString().isEmpty()) {
                        txtCalculations.setText(txtCalculations.getText() + "0");
                        txtCalculations.setText(txtCalculations.getText() + ".");
                        isDecimal = true;
                        canAdd = true;
                        canSub = true;
                        canDiv = true;
                        canMult = true;
                    }
                    int l = txtCalculations.getText().toString().length();
                    //Will insert a 0 and a decimal if last character in string is an operator
                    if (txtCalculations.getText().toString().charAt(l - 1) == '+' || txtCalculations.getText().toString().charAt(l - 1) == '-' || txtCalculations.getText().toString().charAt(l - 1) == '/' || txtCalculations.getText().toString().charAt(l - 1) == '*') {
                        StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                        sb.insert(l, '0');
                        sb.insert(l + 1, '.');
                        txtCalculations.setText(sb);
                        isDecimal = true;
                        canAdd = true;
                        canSub = true;
                        canDiv = true;
                        canMult = true;
                    }
                    //If there isn't a decimal near end of string, will add decimal.
                    if (txtCalculations.getText().toString().charAt(l - 1) != '.' && !isDecimal) {
                        StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                        sb.insert(l, '.');
                        txtCalculations.setText(sb);
                        isDecimal = true;
                        canAdd = true;
                        canSub = true;
                        canDiv = true;
                        canMult = true;
                    }
                }else if(!canPush){
                    txtCalculations.setText("0.");
                    isDecimal = true;
                    canPush = true;
                    canAdd = true;
                    canSub = true;
                    canDiv = true;
                    canMult = true;
                    isNeg = false;
                }
            }
        });

        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if allowed then adds addition operator and closed parenthesis if part of string was negative.
                if (canAdd){
                    if (isNeg && txtCalculations.getText().charAt(txtCalculations.getText().length() -1) != ')') {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "+");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "+");
                        isDecimal = false;
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("()")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("(+)") || txtCalculations.getText().toString().contains("(-)") || txtCalculations.getText().toString().contains("(*)") || txtCalculations.getText().toString().contains("(/)")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    canPush = true;
                    canAdd = false;
                    canSub = false;
                    canDiv = false;
                    canMult = false;
                }
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if allowed then adds subtraction operator and closed parenthesis if part of string was negative.
                if (canSub){
                    if (isNeg && txtCalculations.getText().charAt(txtCalculations.getText().length() -1) != ')') {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "-");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "-");
                        isDecimal = false;
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("()")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("(+)") || txtCalculations.getText().toString().contains("(-)") || txtCalculations.getText().toString().contains("(*)") || txtCalculations.getText().toString().contains("(/)")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    canPush = true;
                    canAdd = false;
                    canSub = false;
                    canDiv = false;
                    canMult = false;
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if allowed then adds division operator and closed parenthesis if part of string was negative.
                if (canDiv){
                    if (isNeg && txtCalculations.getText().charAt(txtCalculations.getText().length() -1) != ')') {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "/");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "/");
                        isDecimal = false;
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("()")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("(+)") || txtCalculations.getText().toString().contains("(-)") || txtCalculations.getText().toString().contains("(*)") || txtCalculations.getText().toString().contains("(/)")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    canPush = true;
                    canAdd = false;
                    canSub = false;
                    canDiv = false;
                    canMult = false;
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if allowed then adds multiplication operator and closed parenthesis if part of string was negative.
                if (canMult){
                    if (isNeg && txtCalculations.getText().charAt(txtCalculations.getText().length() -1) != ')') {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "*");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "*");
                        isDecimal = false;
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("()")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().contains("(+)") || txtCalculations.getText().toString().contains("(-)") || txtCalculations.getText().toString().contains("(*)") || txtCalculations.getText().toString().contains("(/)")){
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        btnBackspace.performClick();
                        isNeg = false;
                    }
                    canPush = true;
                    canAdd = false;
                    canSub = false;
                    canDiv = false;
                    canMult = false;
                }
            }
        });

        btnPosNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canPush) {
                    boolean switched = false;
                    boolean emptySwitch = false;
                    int l = txtCalculations.getText().toString().length();
                    for (int i = txtCalculations.getText().toString().length() - 1; i >= 0 && !switched; i--) {
                        if (!txtCalculations.getText().toString().isEmpty() && txtCalculations.getText().toString().charAt(i) == '(' && isNeg){
                            isNeg = true;
                            switched = true;
                        }
                    }
                    //Checks if string is negative
                    if (txtCalculations.getText().toString().equals("(-") || txtCalculations.getText().toString().equals("(")) {
                        txtCalculations.setText("");
                        isNeg = false;
                        emptySwitch = true;
                    }
                    if(!txtCalculations.getText().toString().equals("") && txtCalculations.getText().toString().charAt(l - 1) == '('){
                        StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                        sb.deleteCharAt(l - 1);
                        txtCalculations.setText(sb);
                        isNeg = false;
                    }
                    if(txtCalculations.getText().toString().endsWith(")")){
                        isNeg = true;
                    }
                    switched = false;
                    if (isNeg) {
                        //If positive, will remove open parenthesis and negative sign
                        for (int i = txtCalculations.getText().toString().length() - 1; i > 0 && !switched; i--) {
                            if (txtCalculations.getText().toString().charAt(i) == '-' && txtCalculations.getText().toString().charAt(i - 1) == '(') {
                                if(txtCalculations.getText().toString().charAt(l-1) == ')'){
                                    btnBackspace.performClick();
                                }
                                StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                                sb.deleteCharAt(i);
                                sb.deleteCharAt(i - 1);
                                switched = true;
                                txtCalculations.setText(sb);
                            }
                        }
                        isNeg = false;
                        if(txtCalculations.getText().toString().startsWith("-")){
                            StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                            sb.insert(0, '(');
                            txtCalculations.setText(sb);
                            isNeg = true;
                        }
                    } else if (!isNeg && !txtCalculations.getText().toString().isEmpty()) {
                        if(txtCalculations.getText().toString().endsWith("(")){
                            btnBackspace.performClick();
                        }
                        for (int i = txtCalculations.getText().toString().length() - 1; i >= 0 && !switched; i--) {
                            //When there is an operator near end of string, will insert open parenthesis negative sign after operator.
                            if (l >= 1 && (txtCalculations.getText().toString().charAt(i) == '+' || txtCalculations.getText().toString().charAt(i) == '-' || txtCalculations.getText().toString().charAt(i) == '/' || txtCalculations.getText().toString().charAt(i) == '*') && !txtCalculations.getText().toString().startsWith("-")) {
                                StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                                sb.insert(i+1, '-');
                                sb.insert(i+1, '(');
                                switched = true;
                                txtCalculations.setText(sb);
                                isNeg = true;
                            }
                            //if calculation string is positive and not empty, will insert open parenthesis and negative sign to beginning of string
                            if (i == 0 && txtCalculations.getText().toString().charAt(l-1) != ')') {
                                StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                                sb.insert(i, '(');
                                sb.insert(i + 1, '-');
                                switched = true;
                                txtCalculations.setText(sb);
                                isNeg = true;
                            }
                        }
                    }
                    //If string is empty, will insert open parenthesis and negative sign.
                    if (txtCalculations.getText().toString().isEmpty() && !emptySwitch) {
                        txtCalculations.setText(txtCalculations.getText() + "(-");
                        isNeg = true;
                    }
                }else{
                    txtCalculations.setText("(-");
                    isNeg = true;
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If calculation string is empty, insert 0 to string.
                if(txtCalculations.getText().toString().isEmpty()){
                    txtCalculations.setText("0");
                }
                //Will insert zero to end of calculation string if last character in string is an operator.
                if(txtCalculations.getText().toString().endsWith("+") || txtCalculations.getText().toString().endsWith("-")){
                    txtCalculations.setText(txtCalculations.getText() + "0");
                }
                //Will insert zero to end of calculation string if last character in string is an operator.
                if(txtCalculations.getText().toString().endsWith("/") || txtCalculations.getText().toString().endsWith("*")){
                    txtCalculations.setText(txtCalculations.getText() + "1");
                }
                //Will insert closed parenthesis if last number in calculation string does not have one
                int l = txtCalculations.getText().toString().length();
                if(txtCalculations.getText().toString().charAt(l-1) != ')' && isNeg){
                    StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                    sb.insert(l, ')');
                    txtCalculations.setText(sb);
                }
                for (int i = txtCalculations.getText().toString().length() - 1; i >= 0 && txtCalculations.getText().toString().contains("()"); i--) {
                    if (txtCalculations.getText().toString().charAt(i) == ')' && txtCalculations.getText().toString().charAt(i - 1) == '(') {
                        StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                        sb.insert(i, '0');
                        txtCalculations.setText(sb);
                    }
                }
                try {
                    //Runs full calculation string through a javascript interpreter using Mozilla Rhino
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1);
                    Scriptable scope = context.initStandardObjects();
                    Object result = context.evaluateString(scope, txtCalculations.getText().toString(), "result", 1, null);
                    Log.d("Result", "" + result);

                    //Converts result to float for decimal precision
                    Float floatResult = Float.valueOf(result.toString());

                    //Removes unnecessary extra decimal on whole numbers
                    int i = floatResult.toString().length() - 1;
                        if (floatResult.toString().charAt(i) == '0' && floatResult.toString().charAt(i - 1) == '.'){
                            StringBuilder sb = new StringBuilder(floatResult.toString());
                            sb.deleteCharAt(i);
                            sb.deleteCharAt(i - 1);
                            txtCalculations.setText("");
                            txtCalculations.setText(sb);
                            canPush = false;
                            isDecimal = false;
                            canAdd = true;
                            canSub = true;
                            canDiv = true;
                            canMult = true;
                        }else{
                            txtCalculations.setText("");
                            txtCalculations.setText(txtCalculations.getText() + floatResult.toString());
                            canPush = false;
                        }
                        //If NaN insert 0 to calculation string
                        if(floatResult.toString().contains("NaN") || floatResult.toString().contains("Infinity")){
                            canPush = false;
                            canAdd = false;
                            canSub = false;
                            canDiv = false;
                            canMult = false;
                            isDecimal = false;
                            if(floatResult.toString().equals("Infinity"))
                            {
                                txtCalculations.setText("∞");
                            }
                            if(floatResult.toString().equals("-Infinity"))
                            {
                                txtCalculations.setText("-∞");
                            }
                        }
                        if(floatResult.toString().startsWith("-")) {
                            StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                            sb.insert(0, '(');
                            sb.append(')');
                            txtCalculations.setText(sb);
                            isNeg = true;
                        }
                } catch(Exception e) {
                    txtCalculations.setText("UwU u bwoke it");
                    canPush = false;
                    e.printStackTrace(System.out);
                }
        }});
    }
}