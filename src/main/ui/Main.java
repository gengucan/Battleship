package ui;

import model.Board;
import model.Game;
import persistence.JsonRead;
import persistence.JsonWrite;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/* Images from:
https://images.freeimages.com/images/large-previews/27d/water-splash-1637149.jpg

https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.weizmann.ac.il%2FWeizmannCompass%2Fsections%2Ffeatures%2Fthe-wonde
rs-of-water&psig=AOvVaw3QttEThc0YOSXi9xBrGvAq&ust=1668896197875000&source=images&cd=vfe&ved=0CBEQjhxqFwoTCNDq4f_guPsCFQA
AAAAdAAAAABAD

https://banner2.cleanpng.com/20180219/fbw/kisspng-explosion-download-cool-cartoon-cloud-explosion-5a8ba3341d8d97.036625
1315191007241211.jpg
 */

//Represents the main functionality of the game, including the flow of the game and requesting user inputs
public class Main {

    private static UserInterface userInterface;

    //EFFECTS: Sets up each ship for each player and runs the game until a winner is found
    public static void main(String[] args) {
        userInterface = new UserInterface();
        userInterface.boardPanel2.disableAll();
    }

}