package com.hbs.battleship;

import java.io.Serializable;
import java.util.ArrayList;

// This class will be used to create ships to be placed on the map
public class Ship implements Serializable {

    // Properties of each ship
    ArrayList<Integer> shipArray = new ArrayList<Integer>();
    int shipNumber;

    Ship(int shape, int width, int length, int shipNumber, int startCoord) {

        this.shipNumber = shipNumber;

        if(shape == 0) { // LShape
            shipArray.add(startCoord);

            for(int i = 0; i < length - 1; i++) { // Adds the vertical part of the L
                if(shipArray.get(shipArray.size() - 1) + 13 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            for(int i = 0; i < width - 1; i++) { // Add the horizontal part of the L
                if(shipArray.get(shipArray.size() - 1) + 1 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 1);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

        }

        if(shape == 1) { // UShape
            shipArray.add(startCoord);

            for(int i = 0; i < length - 1; i++) { // Adds the left vertical part
                if(shipArray.get(shipArray.size() - 1) + 13 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            for(int i = 0; i < width; i++) { // Adds the bottom horizontal part
                if(shipArray.get(shipArray.size() - 1) + 1 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 1);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            for(int i = 0; i < length - 1; i++) { // Adds the right vertical part
                if(shipArray.get(shipArray.size() - 1) + 13 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) - 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

        }

        if(shape == 2) { // Plus
            shipArray.add(startCoord + 13*(length/2));

            for(int i = 0; i < width - 1; i++) { // Adds the horizontal part
                if(shipArray.get(shipArray.size() - 1) + 1 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 1);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            shipArray.add(shipArray.get(shipArray.size()/2)); // sets the last element to the vertical section for vertical loop

            for(int i = 0; i < length/2; i++) { // Adds the middle bottom part
                if(shipArray.get(shipArray.size() - 1) + 13 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            shipArray.add(shipArray.get(width/2)); // sets the last element to the vertical section for vertical loop

            for(int i = 0; i < length/2; i++) { // Adds the middle top part
                if(shipArray.get(shipArray.size() - 1) - 13 <= 168 && shipArray.get(shipArray.size() - 1) - 13 >= 0) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) - 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }
        }

        if(shape == 3) { // Half Plus
            shipArray.add(startCoord);

            for(int i = 0; i < length - 1; i++) { // Adds the vertical part
                if(shipArray.get(shipArray.size() - 1) + 13 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 13);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }

            shipArray.add(shipArray.get(shipArray.size()/2) + 1); // sets the last element to the horizontal section for horizontal loop

            for(int i = 0; i < width - 2; i++) { // Adds the middle horizontal part
                if(shipArray.get(shipArray.size() - 1) + 1 <= 168) {
                    shipArray.add(shipArray.get(shipArray.size() - 1) + 1);
                }

                else {
                    System.out.println("ship out of bounds");
                }
            }
        }

        else {
            System.out.println(" ");
        }

        System.out.println(shipArray);

    }

}