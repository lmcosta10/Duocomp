package com.lapps.duocomp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    int RESET = 0;
    int DONE = 1;
    SharedPreferences shared_preferences;

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

        // Set up event listeners
        setUpEventListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update shared preferences
        shared_preferences = getSharedPreferences("Duocomp", MODE_PRIVATE);

        // Update status
        updateStatus();
    }

    protected void setUpEventListeners() {
        // Done button
        Button done_button = findViewById(R.id.donebutton);
        String today = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                new Date()); // user date format: getDefault() inherits form host OS
        done_button.setOnClickListener((v) -> {
            // TODO: delete logs later
            System.out.println("Done button pressed");

            updateVariables(DONE, today);
        });

        // Reset button
        // TODO: delete later. This is a feature for development
        TextView main_text = findViewById(R.id.maintext);
        TextView days_streak_str = findViewById(R.id.days_streak_str);
        Button reset_button = findViewById(R.id.resetbutton);
        reset_button.setOnClickListener((v) -> {
            shared_preferences.edit().putString("last_date", "").apply();
            shared_preferences.edit().putString("days_streak_str", "0").apply();
            main_text.setText(R.string.study);
            days_streak_str.setText(String.valueOf(0));
        });
    }

    protected void updateStatus() {
        String last_date = shared_preferences.getString("last_date", "");

        String today = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                new Date()); // user date format: getDefault() inherits form host OS

        // TODO: delete logs later
        System.out.println(last_date);
        System.out.println(today);

        if (!today.equals(last_date)) {
            updateVariables(RESET, "");
        }
    }

    protected void updateVariables(int type, String today) {
        TextView main_text = findViewById(R.id.maintext);
        TextView days_streak_str = findViewById(R.id.days_streak_str);
        int days_streak = Integer.parseInt(shared_preferences.getString("days_streak_str", "0"));
        String last_date = shared_preferences.getString("last_date", "");

        if (type == DONE && !(today.equals(last_date))) {
            main_text.setText(R.string.done);
            shared_preferences.edit().putString("last_date", today).apply();
            days_streak_str.setText(String.valueOf(days_streak+1));
            shared_preferences.edit().putString("days_streak_str", String.valueOf(days_streak+1)).apply();
        }
        else if (type == RESET) {
            main_text.setText(R.string.study);
        }
    }
}