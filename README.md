# Battleship
For my final summative of Grade 12 Computer Science, we were assigned to re-create a board game in the form of an app. A game I loved playing throughout my childhood was Battleship so I set out to re-create this game for today's youth in a more accessible form (app). This app essentially allows for a two player game of Battleship where players can design there ships and place them where they want. The rest of the game is just simply guessing and attacking your opponent.

### Game Description:
Two players are each given a grid to place down their ships, taking turns. A ship is placed by inputting it's shape, size, and location and pressing create. Players take turns setting up their boards after which they enter the game screen where everything is hidden on a blank grid. Players can take turns guessing random cells until they locate a ship. After all the cells of a ship have been clicked, it is sunk. The first player to sink all the opponent's ships wins. 

### Basic Logic:
For the map placements screen, when a player clicks create, the data is passed in as parameters for a new object creation. This object is created from a “Ship” class which essentially takes in data and spits out an array of cell numbers depending on the coordinates each ship covers. There are two systems of elements for each player and once all ships are created, they are added to individual array lists and are sent to the Game Screen. As for the Game Screen, there is a grid adapter for each grid view that has action listeners on each cell to check for touch inputs. When there is a touch input, the program runs a series of methods to identify that cell and compares it to the arrays passed from the previous screen. If it matches one of the coordinates, that cell color is changed to the ship color, that coordinate is removed from the list, and the “status” of that cell is changed to -1 so it can be ignored when clicked again. If the length of the ship array is 0, that ship has been sunk. If the length of the ships array is 0, that player wins as they have sunk all the ships. Whenever an empty cell is clicked, the color of it is changed to red.

### Demo:
View the demo for this app [here.](https://youtu.be/wh9V4yHcz4A)

### Tools Used:
- **Android Studio** - The tool used to develop the app
- **Java** - The programming language used to code the logic of the app
- **Base Adapter** - Class used to control the color of each cell on the grid
