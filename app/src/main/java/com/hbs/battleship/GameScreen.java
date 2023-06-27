package com.hbs.battleship;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbs.battleship.GameAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen extends AppCompatActivity {

    private boolean isDelayActive = false;

    private Handler handler = new Handler();

    Dialog sinkDialog;
    Dialog doneDialog;

    private Button home;
    private GridView playGrid;
    private GridView playGrid2;
    private GameAdapter adapter;
    private GameAdapter adapter2;
    private int clickedCell;
    private ImageView gameBG;

    private int[] gameArr;
    int[] mapArr = new int[169];
    int[] mapArr2 = new int[169];
    int currentPlayer = 1;
    private int[] gameArr2;
    private int[] mapArray;
    private int[] mapArray2;
    int tilesTouched = 0;
    int tilesTouched2 = 0;
    int totalShots = 0;
    int totalShots2 = 0;
    int activeShips = 0;
    int activeShips2 = 0;

    private ArrayList<Ship> ships;
    private ArrayList<Ship> ships2;

    private TextView tilesText;
    private TextView tilesText2;
    private TextView shotsText;
    private TextView shotsText2;
    private TextView activeText;
    private TextView activeText2;
    private TextView sunkText;
    private TextView sunkText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        home = (Button)findViewById(R.id.goHome);
        playGrid = findViewById(R.id.playGrid);
        playGrid2 = findViewById(R.id.playGrid2);

        gameArr = getIntent().getIntArrayExtra("gameArr");
        gameArr2 = getIntent().getIntArrayExtra("gameArr2");
        mapArray = getIntent().getIntArrayExtra("mapArr");
        mapArray2 = getIntent().getIntArrayExtra("mapArr2");
        ships = (ArrayList<Ship>) getIntent().getSerializableExtra("ships");
        ships2 = (ArrayList<Ship>) getIntent().getSerializableExtra("ships2");

        adapter = new GameAdapter(GameScreen.this, mapArr);
        adapter2 = new GameAdapter(GameScreen.this, mapArr2);
        playGrid.setAdapter(adapter);
        playGrid2.setAdapter(adapter2);

        tilesText = (TextView)findViewById(R.id.tiles);
        tilesText2 = (TextView)findViewById(R.id.tiles2);
        shotsText = (TextView)findViewById(R.id.shots);
        shotsText2 = (TextView)findViewById(R.id.shots2);
        sunkText = (TextView)findViewById(R.id.sunk);
        sunkText2 = (TextView)findViewById(R.id.sunk2);
        activeText = (TextView)findViewById(R.id.active);
        activeText2 = (TextView)findViewById(R.id.active2);

        sinkDialog = new Dialog(this);
        doneDialog = new Dialog(this);

        gameBG = (ImageView)findViewById(R.id.gameBG);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        doneDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                reset();
            }
        });
    }

    int shipsSunken = 0;
    int shipsSunken2 = 0;

    private Runnable switchToP2 = new Runnable() {
        @Override
        public void run() {
            playGrid.setVisibility(View.INVISIBLE);
            playGrid2.setVisibility(View.VISIBLE);
            tilesText2.setVisibility(View.VISIBLE);
            activeText2.setVisibility(View.VISIBLE);
            sunkText2.setVisibility(View.VISIBLE);
            shotsText2.setVisibility(View.VISIBLE);
            tilesText.setVisibility(View.INVISIBLE);
            activeText.setVisibility(View.INVISIBLE);
            sunkText.setVisibility(View.INVISIBLE);
            shotsText.setVisibility(View.INVISIBLE);
            gameBG.setImageResource(R.drawable.two);
            currentPlayer = 2;
            isDelayActive = false;
        }
    };

    private Runnable switchToP1 = new Runnable() {
        @Override
        public void run() {
            playGrid.setVisibility(View.VISIBLE);
            playGrid2.setVisibility(View.INVISIBLE);
            gameBG.setImageResource(R.drawable.one);
            tilesText2.setVisibility(View.INVISIBLE);
            activeText2.setVisibility(View.INVISIBLE);
            sunkText2.setVisibility(View.INVISIBLE);
            shotsText2.setVisibility(View.INVISIBLE);
            tilesText.setVisibility(View.VISIBLE);
            activeText.setVisibility(View.VISIBLE);
            sunkText.setVisibility(View.VISIBLE);
            shotsText.setVisibility(View.VISIBLE);
            currentPlayer = 1;
            isDelayActive = false;
        }
    };
    public void onCellClick(int position) throws InterruptedException {

        if(currentPlayer == 1) {
            executeMove(gameArr, mapArr, ships, position, mapArray, 1);
            adapter.notifyDataSetChanged();
            totalShots += 1;
            activeShips = ships.size() - shipsSunken;
            tilesText.setText(String.valueOf(tilesTouched));
            shotsText.setText(String.valueOf(totalShots));
            sunkText.setText(String.valueOf(shipsSunken));
            activeText.setText(String.valueOf(activeShips));
            triggerDelayedAction();
        }

        else {
            executeMove(gameArr2, mapArr2, ships2, position, mapArray2, 2);
            adapter2.notifyDataSetChanged();
            totalShots2 += 1;
            activeShips2 = ships2.size() - shipsSunken2;
            tilesText2.setText(String.valueOf(tilesTouched2));
            shotsText2.setText(String.valueOf(totalShots2));
            sunkText2.setText(String.valueOf(shipsSunken2));
            activeText2.setText(String.valueOf(activeShips2));
            triggerDelayedAction2();
        }

    }

    private void triggerDelayedAction() {
        if (!isDelayActive) {
            isDelayActive = true;
            handler.postDelayed(switchToP2, 1000); // Delay in milliseconds
        }
    }

    private void triggerDelayedAction2() {
        if (!isDelayActive) {
            isDelayActive = true;
            handler.postDelayed(switchToP1, 1000); // Delay in milliseconds
        }
    }

    public void executeMove(int[] game, int[] map, ArrayList<Ship> shipsList, int index, int[] original, int player) throws InterruptedException {
        if(game[index] > 0) {
            map[index] = original[index];
            Ship clickedShip = shipsList.get(game[index] - 1);
            game[index] = -1;
            clickedShip.shipArray.remove(clickedShip.shipArray.indexOf(index));

            if(player == 1) {
                tilesTouched += 1;
                mapArr = map;
                gameArr = game;
                ships = shipsList;
            }

            else {
                tilesTouched2 += 1;
                mapArr2 = map;
                gameArr2 = game;
                ships2 = shipsList;
            }

            if(clickedShip.shipArray.size() == 0) {
                System.out.println("Ship " + clickedShip.shipNumber + " has been sunk!");

                if(player == 1) {
                    shipsSunken += 1;
                    System.out.println(shipsSunken);
                    System.out.println(ships.size());
                    if(shipsSunken == ships.size()) {
                        System.out.println("GAME OVER");
                        donepopup(findViewById(android.R.id.content).getRootView());
                    }

                    else {
                        sunkpopup(findViewById(android.R.id.content).getRootView());
                    }
                }

                else {
                    shipsSunken2 += 1;
                    System.out.println(shipsSunken2);
                    System.out.println(ships2.size());
                    if(shipsSunken2 == ships2.size()) {
                        System.out.println("GAME OVER");
                        donepopup(findViewById(android.R.id.content).getRootView());
                    }

                    else {
                        sunkpopup(findViewById(android.R.id.content).getRootView());
                    }
                }
            }
        }

        else if(gameArr[index] < 0) {
            map[index] = original[index];
        }

        else {
            map[index] = Color.RED;
        }

    }

    public void sunkpopup(View v) {
        sinkDialog.setContentView(R.layout.sunkpopup);
        sinkDialog.show();
    }

    public void donepopup(View v) {
        doneDialog.setContentView(R.layout.donepopup);
        doneDialog.setCanceledOnTouchOutside(true);
        doneDialog.show();
    }

    public void reset() {
        ships.clear();
        ships2.clear();
        Arrays.fill(mapArr, 0);
        Arrays.fill(mapArr2, 0);
        tilesTouched = 0;
        tilesTouched2 = 0;
        shipsSunken = 0;
        shipsSunken2 = 0;
        activeShips = 0;
        activeShips2 = 0;
        totalShots = 0;
        totalShots2 = 0;
        Arrays.fill(gameArr, 0);
        Arrays.fill(gameArr2, 0);
        Arrays.fill(mapArray, 0);
        Arrays.fill(mapArray2, 0);
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        startActivity(new Intent(GameScreen.this, MainActivity.class));
    }
}