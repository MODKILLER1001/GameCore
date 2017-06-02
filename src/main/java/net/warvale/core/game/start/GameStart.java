package net.warvale.core.game.start;


import java.util.ArrayList;

/**
 * Created by AAces on 6/2/2017.
 */
public class GameStart {

    //insert detect 1 player on each team code here

    public static boolean votingActive = true;
    public static ArrayList<String> voted = new ArrayList<>();
    public static int rfvotes = 0;
    public static int vivotes = 0;
    public static int pevotes = 0;
    public static int etvotes = 0;

    //clear arraylist 'voted'

    /*start countdown, send a message to players ingame telling them to vote for a map,
    * and show a list of maps and numbers corresponding to them, set votingActive to true
    * Something like:
    *
    * /vote # to vote for a map!
    * 1: Redwood Forest
    * 2: Volcano Island
    * 3: Pagoda Everglade
    * 4: Extraterrestrial
    *
    * after time is up, set votingActive to false and detect which map was the most voted for - the four integers above will represent how many times each map was voted for
    *
     */


}
