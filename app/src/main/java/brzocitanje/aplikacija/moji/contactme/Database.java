package brzocitanje.aplikacija.moji.contactme;

import java.util.ArrayList;

/**
 * Created by Pedja on 11-Jan-18.
 */

public class Database {

    private static ArrayList<Dataitems> items;

    public static ArrayList<Dataitems> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Dataitems> items) {

        Database.items = items;
    }
    public static void clearList(){
        Database.items.clear();
    }
}
