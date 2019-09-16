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
    boolean isEmpty;
    boolean isDecimal = false;
    boolean canAdd, canSub, canDiv, canMult;

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
                txtCalculations.setText("");
                isNeg = false;
                isDecimal = false;
            }
        });

        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = txtCalculations.getText().toString();
                int l = txtCalculations.getText().toString().length();

                if(number.length() > 0){
                    number = number.substring(0, number.length() - 1);
                    if(txtCalculations.getText().toString().charAt(l-1)=='('){
                        isNeg = false;
                    }
                }
                txtCalculations.setText(number);
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "1");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "2");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "3");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "4");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "5");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "6");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "7");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "8");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "9");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCalculations.setText(txtCalculations.getText() + "0");
                canAdd = true;
                canSub = true;
                canDiv = true;
                canMult = true;
            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCalculations.getText().toString().equals("")){
                    txtCalculations.setText(txtCalculations.getText() + "0");
                    txtCalculations.setText(txtCalculations.getText() + ".");
                    isDecimal = true;
                }
                int l = txtCalculations.getText().toString().length();

                if(txtCalculations.getText().toString().charAt(l-1) == '+' || txtCalculations.getText().toString().charAt(l-1) == '-' || txtCalculations.getText().toString().charAt(l-1) == '/' || txtCalculations.getText().toString().charAt(l-1) == '*'){
                    StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                    sb.insert(l, '0');
                    sb.insert(l+1, '.');
                    txtCalculations.setText(sb);
                    isDecimal = true;
                }

                if(txtCalculations.getText().toString().charAt(l-1) != '.' && !isDecimal){
                    StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                    sb.insert(l, '.');
                    txtCalculations.setText(sb);
                    isDecimal = true;
                }
            }
        });

        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canAdd){
                    if (isNeg) {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "+");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "+");
                        isDecimal = false;
                    }
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
                if (canSub){
                    if (isNeg) {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "-");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "-");
                        isDecimal = false;
                    }
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
                if (canDiv){
                    if (isNeg) {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "/");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "/");
                        isDecimal = false;
                    }
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
                if (canMult){
                    if (isNeg) {
                        txtCalculations.setText(txtCalculations.getText() + ")");
                        txtCalculations.setText(txtCalculations.getText() + "*");
                        isNeg = false;
                        isDecimal = false;
                    } else {
                        txtCalculations.setText(txtCalculations.getText() + "*");
                        isDecimal = false;
                    }
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

                boolean switched = false;
                boolean emptySwitch = false;

                if(txtCalculations.getText().toString().equals("(-")){
                    txtCalculations.setText("");
                    isNeg = false;
                    emptySwitch = true;
                }
                if(isNeg) {
                    for (int i = txtCalculations.getText().toString().length() - 1; i >= 0 && !switched; i--) {
                        if (txtCalculations.getText().toString().charAt(i) == '-' && txtCalculations.getText().toString().charAt(i - 1) == '(') {
                            StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                            sb.deleteCharAt(i);
                            sb.deleteCharAt(i - 1);
                            switched = true;
                            txtCalculations.setText(sb);
                        }
                    }
                    isNeg = false;
                }else if(!isNeg && !isEmpty){
                    for (int i = txtCalculations.getText().toString().length() - 1; i >= 0 && !switched; i--) {
                        if (txtCalculations.getText().toString().charAt(i) == '+' || txtCalculations.getText().toString().charAt(i) == '-' || txtCalculations.getText().toString().charAt(i) == '/' || txtCalculations.getText().toString().charAt(i) == '*') {
                            StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                            sb.insert(i+1, '(');
                            sb.insert(i+2, '-');
                            switched = true;
                            txtCalculations.setText(sb);
                        }
                        if(i==0){
                            StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                            sb.insert(i, '(');
                            sb.insert(i+1, '-');
                            switched = true;
                            txtCalculations.setText(sb);
                        }
                    }
                    isNeg=true;
                }
                String calculations = txtCalculations.getText().toString();
                if(txtCalculations.getText().toString().isEmpty() && !emptySwitch){
                    txtCalculations.setText(txtCalculations.getText() + "(-");
                    isNeg = true;
                }

            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = txtCalculations.getText().toString().length();
                if(txtCalculations.getText().toString().charAt(l-1) != ')' && isNeg){
                    StringBuilder sb = new StringBuilder(txtCalculations.getText().toString());
                    sb.insert(l, ')');
                    txtCalculations.setText(sb);
                }

                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scope = context.initStandardObjects();
                Object result = context.evaluateString(scope, txtCalculations.getText().toString(), "result", 1, null);
                Log.d("Result", "" + result);
                if (txtCalculations.getText().toString().contains(".") || result.toString().length() >= 10 || Double.valueOf(result.toString()) < 1) {
                    txtCalculations.setText("");
                    txtCalculations.setText(txtCalculations.getText() + result.toString());
                }else if(result.toString().length() < 10){
                    Double doubleResult = Double.valueOf(result.toString());
                    Integer intResult = doubleResult.intValue();
                    txtCalculations.setText("");
                    txtCalculations.setText(txtCalculations.getText() + intResult.toString());
                }
            }
        });
    }
}
