package com.lapps.duocomp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int done_day = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set initial content
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Event listeners
        // Update on button click
        TextView main_text = (TextView) findViewById(R.id.maintext);
        Button button = (Button) findViewById(R.id.donebutton);
        Calendar calendar = Calendar.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                main_text.setText("Done for today!");
                done_day = calendar.get(Calendar.DAY_OF_MONTH);
            }
        });


    }

    @Override
    protected void onResume () {
        super.onResume();

        TextView main_text = (TextView) findViewById(R.id.maintext);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);

        if (today != done_day) {
            main_text.setText("Study!");
        }

        System.out.println(today);
        System.out.println(done_day);
    }
}