package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CalculatorActivity extends AppCompatActivity {

    private EditText kWh;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button calculateBtn = findViewById(R.id.calculateBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        kWh = findViewById(R.id.kWh);
        output = findViewById(R.id.output);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kWh.setText("");
                output.setText("");
            }
        });
    }
    private void calculateBill(){

        int units =Integer.parseInt(kWh.getText().toString());
        double billAmount = 0;
        double percentage;

        if (units <= 200) {
            billAmount = units * 0.218;
        } else if (units <= 300) {
            billAmount = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            billAmount = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            billAmount = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        double rebate = 0;

        if(units <=200){
            rebate = 0.01;
        }
        else if(units <=300){
            rebate = 0.02;
        }
        else if(units <=600){
            rebate = 0.03;
        }
        else{
            rebate = 0.05;
        }


        billAmount -= (billAmount * rebate);
        percentage = rebate * 100;
        String result = "Your Total bill is: RM  " + String.format("%.2f", billAmount) + " And have received a " + percentage + " % rebate";
        output.setText(result);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.home:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;

            case R.id.calculation:
                Intent intent3 = new Intent(this, CalculatorActivity.class);
                startActivity(intent3);
                break;

            default:

        }

        return super.onOptionsItemSelected(item);
    }
}