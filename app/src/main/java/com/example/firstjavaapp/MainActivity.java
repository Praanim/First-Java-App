package com.example.firstjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Button btn2;

    private TextView textView;

    private EditText editText;

//    private boolean isCleared = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.hello);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);


        //Toast which shows up when the textfield is empty.
        final Toast toast = Toast.makeText(MainActivity.this,"First Please Enter Some Value",Toast.LENGTH_SHORT);


        //on button select event
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                      final String s = editText.getText().toString();

                      if(TextUtils.isEmpty(s)){
                          toast.show();
                      }else{
                          try {
                              final Integer userVal = Integer.parseInt(s);

                              final double pound = userVal * 2.5;

                              textView.setText("The value in the pound is " + pound);
                          }catch (NumberFormatException e){
                                Toast.makeText(v.getContext(),e.toString(),Toast.LENGTH_SHORT).show();
                          }

                      }

                      //close keyboard
                      closeKeyBoard();



                    }
                }
        );

        //clear btn on click listener
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(editText.getText().toString())){
                            toast.show();

                        }else{
                            editText.getText().clear();
                            textView.setText("");

                        }
                    }
                }
        );
    }

    private void closeKeyBoard(){
        View view = getCurrentFocus();

        if(view != null){
            InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

          final boolean isKeyBoardOpen = imm.isActive(view);
            System.out.println(isKeyBoardOpen);

            if(isKeyBoardOpen){
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }

        }
    }
}