//Pablo Mateos García

package com.mateosgarciapablo.task1_subnetcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Elements of the layout

    private EditText oct1EditText;
    private EditText oct2EditText;
    private EditText oct3EditText;
    private EditText oct4EditText;
    private EditText sm1EditText;
    private EditText sm2EditText;
    private EditText sm3EditText;
    private EditText sm4EditText;
    private EditText ipClassEditTExt;
    private ListView networkListView;
    private ListView hostRangeListView;
    private ListView broadcastListView;
    private Button calculateButton;
    private Button aboutButton;
    private Button helpButton;

    //Aditional variables

    private View clickSource;
    private View touchSource;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> listNetworks;
    private ArrayList<String> listHosts;
    private ArrayList<String> listBroadcast;
    private static int oct1, oct2, oct3, oct4, sm1, sm2, sm3, sm4;
    private static char ipClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up controls

        setUp();
    }

    private void setUp(){

        //Link variables with their layout elements and set filters in the EditTexts

        oct1EditText = findViewById(R.id.oct1Tv);
        oct1EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        oct2EditText = findViewById(R.id.oct2Tv);
        oct2EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        oct3EditText = findViewById(R.id.oct3Tv);
        oct3EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        oct4EditText = findViewById(R.id.oct4Tv);
        oct4EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        sm1EditText = findViewById(R.id.sm1Tv);
        sm1EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        sm2EditText = findViewById(R.id.sm2Tv);
        sm2EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        sm3EditText = findViewById(R.id.sm3Tv);
        sm3EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        sm4EditText = findViewById(R.id.sm4Tv);
        sm4EditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        ipClassEditTExt = findViewById(R.id.ipClassTypeTv);
        networkListView = findViewById(R.id.network_list_view);
        hostRangeListView = findViewById(R.id.host_range_list_view);
        broadcastListView = findViewById(R.id.broadcast_list_view);
        calculateButton = findViewById(R.id.calculateBt);
        aboutButton = findViewById(R.id.aboutBt);
        helpButton = findViewById(R.id.helpBt);

        listNetworks = new ArrayList<>();
        listHosts = new ArrayList<>();
        listBroadcast = new ArrayList<>();

        //Link the scrolls od the three ListViews

        networkListView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event){

                if(touchSource == null)
                    touchSource = v;

                if(v == touchSource){
                    hostRangeListView.dispatchTouchEvent(event);
                    broadcastListView.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });

        hostRangeListView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event){

                if(touchSource == null)
                    touchSource = v;

                if(v == touchSource) {
                    networkListView.dispatchTouchEvent(event);
                    broadcastListView.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });

        broadcastListView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event){

                if(touchSource == null)
                    touchSource = v;

                if(v == touchSource) {
                    hostRangeListView.dispatchTouchEvent(event);
                    networkListView.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });

        //Set buttons

        calculateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                calculate();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void calculate(){

        //Clear the current lists

        listNetworks.clear();
        listHosts.clear();
        listBroadcast.clear();

        //Check if there are empty fields

        if(checkVoids()==true){
            error();
            return;
        }

        //Pass the values of the EditTexts to the variables

        oct1 = Integer.parseInt(oct1EditText.getText().toString());
        oct2 = Integer.parseInt(oct2EditText.getText().toString());
        oct3 = Integer.parseInt(oct3EditText.getText().toString());
        oct4 = Integer.parseInt(oct4EditText.getText().toString());
        sm1 = Integer.parseInt(sm1EditText.getText().toString());
        sm2 = Integer.parseInt(sm2EditText.getText().toString());
        sm3 = Integer.parseInt(sm3EditText.getText().toString());
        sm4 = Integer.parseInt(sm4EditText.getText().toString());

        //Check class by first octect and set it

        ipClass = whatClass(oct1);
        ipClassEditTExt.setText(String.valueOf(ipClass));

        //Check if the IP and the Subnet Mask are compatible

        if(!verify()){
            error();
            return;
        }

        //Pass subnet mask to binary and count 1s

        String binString;

        binString = Integer.toBinaryString(sm1) + Integer.toBinaryString(sm2) + Integer.toBinaryString(sm3) + Integer.toBinaryString(sm4);

        int count = 0;

        for (int loop = 0; loop < binString.length(); loop++){
            if (binString.charAt(loop) == '1'){
                count++;
            }
        }

        //List the subnet masks depending on the class

        switch (whatClass()){
            case 'A':{

                //Perform calculations

                int extraBits = count - 8;

                int number_of_Networks = (int) Math.pow(2, extraBits);

                String str = "";

                //Create separated lists

                sm1 = invertBits(sm1);
                sm2 = invertBits(sm2);
                sm3 = invertBits(sm3);
                sm4 = invertBits(sm4);

                for (int loop = 1; loop <= number_of_Networks; loop++){

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listNetworks.add(str);

                    oct4++;

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4 + " - ";

                    oct1 = oct1 + sm1;
                    oct2 = oct2 + sm2;
                    oct3 = oct3 + sm3;
                    oct4 = oct4 + sm4 - 2;

                    str = str + oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    oct4++;

                    listHosts.add(str);


                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listBroadcast.add(str);

                    oct2++;
                    oct3=0;
                    oct4=0;
                }
            }
            break;

            case 'B':{

                //Perform calculations

                int extraBits = count - 16;

                int number_of_Networks = (int) Math.pow(2, extraBits);

                String str = "";

                //Create separated lists

                sm1 = invertBits(sm1);
                sm2 = invertBits(sm2);
                sm3 = invertBits(sm3);
                sm4 = invertBits(sm4);

                for (int loop = 1; loop <= number_of_Networks; loop++){
                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listNetworks.add(str);

                    oct4++;

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4 + " \t ";

                    oct1 = oct1 + sm1;
                    oct2 = oct2 + sm2;
                    oct3 = oct3 + sm3;
                    oct4 = oct4 + sm4 - 2;

                    str = str + oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listHosts.add(str);


                    oct4++;

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    oct3++;
                    oct4=0;

                    listBroadcast.add(str);
                }
            }
            break;
            case 'C':{

                //Perform calculations

                int hostBits = 32 - count;

                int hosts_per_Network = (int) Math.pow(2, hostBits) - 2;

                int extraBits = count - 24;

                int number_of_Networks = (int) Math.pow(2, extraBits);

                String str = "";

                //Create separated lists

                for (int loop = 1; loop <= number_of_Networks; loop++){
                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listNetworks.add(str);

                    oct4++;

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4 + " - ";

                    oct4 = oct4 + hosts_per_Network - 1;

                    str = str + oct1 + "." + oct2 + "." + oct3 + "." + oct4;
                    listHosts.add(str);


                    oct4++;

                    str = oct1 + "." + oct2 + "." + oct3 + "." + oct4;

                    listBroadcast.add(str);

                    oct4++;
                }
            }
            break;
        }

        //Set the adapters of the three ListViews

        arrayAdapter = new ArrayAdapter(this, R.layout.list_text_view, listNetworks);
        networkListView.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this, R.layout.list_text_view, listHosts);
        hostRangeListView.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this, R.layout.list_text_view, listBroadcast);
        broadcastListView.setAdapter(arrayAdapter);
    }

    private boolean verify(){

        //Check if the subnet mask is valid for the IP address

        if(ipClass > whatClass()){
            return false;
        }
        return true;
    }

    public static char whatClass(int oct1){

        //Decide the class by the first octect

        if(oct1 >= 1 && oct1 <= 126){
            return 'A';
        }
        else if(oct1 >= 128 && oct1 <= 191){
            return 'B';
        }
        else if (oct1 >= 192 && oct1 <= 223){
            return 'C';
        }
        else {
            return '?';
        }
    }

    public static char whatClass(){

        //Decide the class by the subnet mask

        if(sm1 == 255){
            if(sm2 == 255){
                if(sm3 == 255){
                    return 'C';
                }
                else{
                    return 'B';
                }
            }
        }
        return 'A';
    }

    private void error(){

        //If the data introduced is wrong or there are empty fields

        Toast.makeText(this.getBaseContext(), "Some of the data are incorrect.", Toast.LENGTH_SHORT).show();
    }

    private boolean checkVoids(){

        //Check if there are empty fields

        if(oct1EditText.getText().toString().equals("") ||
                oct2EditText.getText().toString().equals("") ||
                oct3EditText.getText().toString().equals("") ||
                oct4EditText.getText().toString().equals("") ||
                sm1EditText.getText().toString().equals("") ||
                sm2EditText.getText().toString().equals("") ||
                sm3EditText.getText().toString().equals("") ||
                sm4EditText.getText().toString().equals("")){
            return true;
        }
        return false;
    }

    public int invertBits(int value){

        //Inverts the bits

        int val = value;
        val = ~val & 0xff;
        return val;
    }

}
