package com.hbs.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView wheel;
    private Button screenChooser;

    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheel = (ImageView)findViewById(R.id.wheel);
        screenChooser = (Button)findViewById(R.id.screenChooser);

        String[] screens = {"Instructions", "Placements", "Maps"};

        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheel.setPivotX(wheel.getWidth()/2);
                wheel.setPivotY(wheel.getHeight()/2);
                wheel.setRotation(wheel.getRotation() + 60);
                wheel.invalidate();
                screenChooser.setText(screens[counter]);
                counter += 1;

                if (counter == screens.length) {
                     counter = 0;
                }

            }
        });

        screenChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(screenChooser.getText() == screens[0]) {
                    startActivity(new Intent(MainActivity.this, Instructions.class));
                }

                else if(screenChooser.getText() == screens[1]) {
                    startActivity(new Intent(MainActivity.this, MapPlacements.class));
                }

                else {
                    System.out.println("To be Added");
                }
            }
        });
    }
}