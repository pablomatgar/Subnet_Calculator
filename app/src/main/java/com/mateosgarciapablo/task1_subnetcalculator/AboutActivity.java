//Pablo Mateos García

package com.mateosgarciapablo.task1_subnetcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Set up controls

        setUpControls();
    }

    private void setUpControls() {

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view);
        infoTextView = findViewById(R.id.info_text_view);
        backButton = findViewById(R.id.back_button_a);

        //Set the texts

        titleTextView.setText("About this app");
        infoTextView.setText("This app allows you to calculate an IPv4 subnet range.\n\n\n" +
                "You can enter a IPv4 address and subnet mask. If they are correct data, the application will inform you of what class of address the IPv4 address is and the range(s) of subnet(s) available depending on the subnet mask entered. If there is something wrong, you will be notified.\n\n\n" +
                "If you have any doubt about the app, please click on the help button.");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}
