//Pablo Mateos García

package com.mateosgarciapablo.task1_subnetcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Set up controls

        setUpControls();
    }

    private void setUpControls(){

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view_h);
        infoTextView = findViewById(R.id.info_text_view_h);
        backButton = findViewById(R.id.back_button_h);

        //Set the texts

        titleTextView.setText("Help");
        infoTextView.setText("To use this app, you should introduce a IPv4 address and a subnet mask in the main screen.\n\n\n" +
                "Then, with clicking on the 'Calculate' button you will be able to see the class of the IP you introduced and all the the range(s) of subnet(s) available, depending on the subnet mask introduced, will be shown underneath.\n\n" +
                "If you have more question, you can contact me at:\n\n\n" +
                "S19001531@mail.glyndwr.ac.uk");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}
