package com.hbs.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MapPlacements extends AppCompatActivity {

    // Initializing all UI Elements
    private Button player1MapPlacement;
    private Button player2MapPlacement;
    private Button goBtn;
    private Button LShapeBtn;
    private Button HalfPlusShapeBtn;
    private Button PlusShapeBtn;
    private Button UShapeBtn;
    private Button reset;
    private Button createShip;
    private Button createShip2;

    private EditText widthTI;
    private EditText heightTI;
    private EditText startTI;
    private GridView mapPlacements1Grid;
    private GridView mapPlacements2Grid;
    private GridAdapter mGridAdapter;

    // Variable for creations of ships
    int[] mapArray = new int[169];
    int[] mapArray2 = new int[169];

    int[] gameArray = new int[169];
    int[] gameArray2 = new int[169];

    int shipNum = 1;
    int shipNum2 = 1;
    int width = 1;
    int height = 1;
    int startCoord = 0;
    int shape = 0;
    ArrayList<Ship> ships = new ArrayList<Ship>();
    ArrayList<Ship> ships2 = new ArrayList<Ship>();

    GridAdapter adap1;
    GridAdapter adap2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_placements);

        // Referencing UI Elements
        UShapeBtn = (Button)findViewById(R.id.UBtn);
        HalfPlusShapeBtn = (Button)findViewById(R.id.HalfPlusBtn);
        PlusShapeBtn = (Button)findViewById(R.id.PlusBtn);
        LShapeBtn = (Button)findViewById(R.id.LBtn);
        goBtn = (Button)findViewById(R.id.goBtn);
        createShip = (Button)findViewById(R.id.createShip);
        createShip2 = (Button)findViewById(R.id.createShip2);
        player1MapPlacement = (Button)findViewById(R.id.p1Btn);
        player2MapPlacement = (Button)findViewById(R.id.p2Btn);
        startTI = (EditText)findViewById(R.id.startTI);
        widthTI = (EditText)findViewById(R.id.widthTI);
        heightTI = (EditText)findViewById(R.id.heightTI);
        mapPlacements1Grid = findViewById(R.id.grid1);
        mapPlacements2Grid = findViewById(R.id.grid2);
        reset = findViewById(R.id.reset);

        // For Shapes:
        LShapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape = 0;
            }
        });

        UShapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape = 1;
            }
        });

        HalfPlusShapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape = 3;
            }
        });

        PlusShapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape = 2;
            }
        });
        // ---------------------------------------------------------------------

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ships.clear();
                ships2.clear();
                shape = 0;
                startTI.getText().clear();
                widthTI.getText().clear();
                heightTI.getText().clear();
                Arrays.fill(mapArray, 0);
                Arrays.fill(mapArray2, 0);
                shipNum = 1;
                shipNum2 = 1;
                width = 1;
                height = 1;
                startCoord = 0;
                shape = 0;
                adap1.notifyDataSetChanged();
                adap2.notifyDataSetChanged();
            }
        });

        // Player Maps:
        player1MapPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapPlacements2Grid.setVisibility(View.INVISIBLE);
                mapPlacements1Grid.setVisibility(View.VISIBLE);
                createShip.setVisibility(View.VISIBLE);
                createShip2.setVisibility(View.INVISIBLE);
            }
        });

        player2MapPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapPlacements1Grid.setVisibility(View.INVISIBLE);
                mapPlacements2Grid.setVisibility(View.VISIBLE);
                createShip2.setVisibility(View.VISIBLE);
                createShip.setVisibility(View.INVISIBLE);
            }
        });
        // ---------------------------------------------------------------------

        adap1 = new GridAdapter(MapPlacements.this, mapArray);
        adap2 = new GridAdapter(MapPlacements.this, mapArray2);

        mapPlacements1Grid.setAdapter(adap1);
        mapPlacements2Grid.setAdapter(adap2);

        createShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                width = Integer.parseInt(String.valueOf(widthTI.getText()));
                height = Integer.parseInt(String.valueOf(heightTI.getText()));
                startCoord = Integer.parseInt(String.valueOf(startTI.getText()));

                ships.add(new Ship(shape, width, height, shipNum, startCoord));

                Random random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);

                int randomColor = Color.rgb(red, green, blue);

                for (int i = 0; i < ships.get(ships.size() - 1).shipArray.size(); i++) {
                    mapArray[ships.get(ships.size() - 1).shipArray.get(i)] = randomColor;
                    gameArray[ships.get(ships.size() - 1).shipArray.get(i)] = shipNum;
                }
                shipNum += 1;

                adap1.notifyDataSetChanged();
                shape = 0;
                startTI.getText().clear();
                widthTI.getText().clear();
                heightTI.getText().clear();
            }
        });

        createShip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                width = Integer.parseInt(String.valueOf(widthTI.getText()));
                height = Integer.parseInt(String.valueOf(heightTI.getText()));
                startCoord = Integer.parseInt(String.valueOf(startTI.getText()));

                ships2.add(new Ship(shape, width, height, shipNum, startCoord));

                Random random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);

                int randomColor = Color.rgb(red, green, blue);

                for (int i = 0; i < ships2.get(ships2.size() - 1).shipArray.size(); i++) {
                    mapArray2[ships2.get(ships2.size() - 1).shipArray.get(i)] = randomColor;
                    gameArray2[ships2.get(ships2.size() - 1).shipArray.get(i)] = shipNum2;
                }
                shipNum2 += 1;

                adap2.notifyDataSetChanged();
                shape = 0;
                startTI.getText().clear();
                widthTI.getText().clear();
                heightTI.getText().clear();
            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });

    }

    public void openGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("gameArr", gameArray);
        intent.putExtra("gameArr2", gameArray2);
        intent.putExtra("mapArr", mapArray);
        intent.putExtra("mapArr2", mapArray2);
        intent.putExtra("ships", ships);
        intent.putExtra("ships2", ships2);
        startActivity(intent);
    }
}

