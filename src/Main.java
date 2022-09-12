import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        //Ash's original(Kanto) team + psyduck(best pokemon)
        ArrayList<Pokemon> team = new ArrayList<Pokemon>();

        // Psyduck creation block
        String psyduckWeak = "Grass Electric";
        Attack[] psyduckMoveset = new Attack[4];
        psyduckMoveset[0] = new Attack("Water Gun", 40);
        psyduckMoveset[1] = new Attack("Hydro Pump", 60);
        psyduckMoveset[2] = new Attack("Ice Beam", 50);
        psyduckMoveset[3] = new Attack("Recover", 0);
        Pokemon psyduck = new Pokemon("Psyduck", 90, 85, 60, "Water", psyduckWeak, psyduckMoveset, 34);
        team.add(psyduck);

        // Charizard creation block, this Pokémon doesn't get enough love
        String charizardWeak = "Water Ground Rock";
        Attack[] charizardMoveset = new Attack[4];
        charizardMoveset[0] = new Attack("Ember", 40);
        charizardMoveset[1] = new Attack("Fire Fang", 45);
        charizardMoveset[2] = new Attack("Fire Spin", 35);
        charizardMoveset[3] = new Attack("Recover", 0);
        Pokemon charizard = new Pokemon("Charizard", 170, 170, 42, "Fire", charizardWeak, charizardMoveset, 31);
        team.add(charizard);

        // Bulbasaur creation block
        String bulbasaurWeak = "Fire Ice Poison Flying Bug";
        Attack[] bulbasaurMoveset = new Attack[4];
        bulbasaurMoveset[0] = new Attack("Growl", 0);
        bulbasaurMoveset[1] = new Attack("Vine Whip", 40);
        bulbasaurMoveset[2] = new Attack("Razor Leaf", 45);
        bulbasaurMoveset[3] = new Attack("Seed Bomb", 70);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 54, 54, 12, "Grass", bulbasaurWeak, bulbasaurMoveset, 32);
        team.add(bulbasaur);

        // Pidgeot creation block
        String pidgeotWeak = "Fighting";
        Attack[] pidgeotMoveset = new Attack[4];
        pidgeotMoveset[0] = new Attack("Gust", 40);
        pidgeotMoveset[1] = new Attack("Hurricane", 50);
        pidgeotMoveset[2] = new Attack("Tackle", 20);
        pidgeotMoveset[3] = new Attack("Aerial Ace", 60);
        Pokemon pidgeot = new Pokemon("Pidgeot", 200, 200, 45, "Normal", pidgeotWeak, pidgeotMoveset, 37);
        team.add(pidgeot);

        // Butterfree creation block
        String butterfreeWeak = "Fire Flying Rock" ;
        Attack[] butterfreeMoveset = new Attack[4];
        butterfreeMoveset[0] = new Attack("Bug Bite", 60);
        butterfreeMoveset[1] = new Attack("Psybeam", 55);
        butterfreeMoveset[2] = new Attack("Tackle", 20);
        butterfreeMoveset[3] = new Attack("Recover", 0);
        Pokemon butterfree = new Pokemon("Butterfree", 114, 114, 29, "Bug", butterfreeWeak, butterfreeMoveset, 35);
        team.add(butterfree);

        // Squirtle creation block
        String squirtleWeak = "Grass Electric";
        Attack[] squirtleMoveset = new Attack[4];
        squirtleMoveset[0] = new Attack("Water Gun", 40);
        squirtleMoveset[1] = new Attack("Aqua Tail", 60);
        squirtleMoveset[2] = new Attack("Water Pulse", 45);
        squirtleMoveset[3] = new Attack("Recover", 0);
        Pokemon squirtle = new Pokemon("Squirtle", 160, 160, 47, "Water", squirtleWeak, squirtleMoveset, 34);
        team.add(squirtle);

        //pokemon declaration done
        //Creation of Bag and all items:
        Item[] Bag = new Item[3];
        Bag[0] = new Item("Potion", 6, 30, false);
        Bag[1] = new Item("Super Potion", 2, 80, false);
        Bag[2] = new Item("Pokeball", 3, 0, true);

        // boolean isGameOver measures if the game is over and decides when to stop the loop of moves
        boolean isGameOver = false;
        Pokemon currentPokemon = team.get(0);
        String HoohWeak = "Water Electric Rock";
        Attack[] HoohMoveset = new Attack[4];
        HoohMoveset[0] = new Attack("Sacred Fire", 100);
        HoohMoveset[1] = new Attack("Fire Blast", 55);
        HoohMoveset[2] = new Attack("Burn", 30);
        HoohMoveset[3] = new Attack("Sky Attack", 50);
        Pokemon enemyPokemon = new Pokemon("Ho-oh", 1060, 1060, 79, "Fire", HoohWeak, HoohMoveset, 31);

        //this while loop determines the moves of the game, starts the game
        System.out.println("The Battle Has Begun!");
        while(!isGameOver){
            PrintGame("What will you do:", currentPokemon, enemyPokemon);
            Scanner Scan = new Scanner(System.in);
            String input = Scan.nextLine().toLowerCase(Locale.ROOT);
            enum state {
                att, bag, pok, run
            }
            state gameOption = state.valueOf(input);

            switch(gameOption){
                case att:
                    PrintAttackMenu(currentPokemon); //printing out the choices of attacks
                    String attack = Scan.nextLine().toLowerCase(Locale.ROOT); //Scans in user input
                    attSwitch(currentPokemon, enemyPokemon, attack);
                    break;

                case bag:
                    PrintBag(Bag);
                    String itemUsed = Scan.nextLine().toLowerCase(Locale.ROOT);
                    for (int i = 0; i<Bag.length; i++){
                        if (Bag[i].getName().toLowerCase(Locale.ROOT).equals(itemUsed) && Bag[i].getQuantity() > 0){
                            Bag[i].setQuantity(Bag[i].getQuantity() - 1);
                            //checks if the item is a pokeball, if it is, this ends the game
                            if (Bag[i].isPokeball){
                                PrintGame("you caught the enemy pokemon", currentPokemon, enemyPokemon);
                                System.exit(0);
                            }
                            //since the item must be a potion, this else if makes sure you can't heal above max HP, in the case that
                            //the pokemon is trying to use a potion where it would heal above max HP, this sets HP to max
                            else if (currentPokemon.getHP() > currentPokemon.getMaxHp() - Bag[i].getHealAmount()){
                                currentPokemon.setHP(currentPokemon.getMaxHp());
                                PrintGame("You healed your Pokemon to Max HP!", currentPokemon, enemyPokemon);
                            }
                            //heals the pokemon normally since it is not within max HP healing range
                            else {
                                currentPokemon.setHP(currentPokemon.getHP() + Bag[i].getHealAmount());
                                PrintGame("Your Pokemon has been healed by a " + Bag[i].getName(), currentPokemon, enemyPokemon);
                            }
                        }
                    }

                    break;

                case pok:
                    PrintTeam(team);
                    String swap = Scan.nextLine().toLowerCase(Locale.ROOT);
                    for (int i = 0; i< team.size(); i++){
                        if (swap.equals(team.get(i).getName().toLowerCase(Locale.ROOT))){
                            currentPokemon = team.get(i);
                        }
                    }

                    PrintGame(currentPokemon.getName() + " was swapped in!", currentPokemon, enemyPokemon);
                    break;

                case run:
                    PrintGame("Its a 1v6 no way your running", currentPokemon, enemyPokemon);
                    System.exit(0);
                    break;
            }
            Random rand = new Random();
            int enemyTurn = rand.nextInt(6);
            switch (enemyTurn) {
                case 1:
                    if (currentPokemon.getWeakness().contains(enemyPokemon.getType())) {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[0].getDamage() * 2 * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[0].getName() + " it was super effective!", currentPokemon, enemyPokemon);
                    }
                    else {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[0].getDamage() * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[3].getName(), currentPokemon, enemyPokemon);
                    }
                    if (currentPokemon.getHP() <= 0 && team.size() > 1) {
                        PrintGame("Your pokemon has fainted! Choose a different pokemon to be swapped in!", currentPokemon, enemyPokemon);
                        team.remove(currentPokemon);
                        PrintTeam(team);
                        String swap = Scan.nextLine().toLowerCase(Locale.ROOT);
                        for (int i = 0; i< team.size(); i++){
                            if (swap.equals(team.get(i).getName().toLowerCase(Locale.ROOT))){
                                currentPokemon = team.get(i);
                            }
                        }
                    }
                    else if (currentPokemon.getHP() <= 0 && team.size() == 1){
                        PrintGame("You have no Pokemon remaining, you lose!", currentPokemon, enemyPokemon);
                        System.exit(0);
                    }
                    break;
                case 2:
                    if (currentPokemon.getWeakness().contains(enemyPokemon.getType())) {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[1].getDamage() * 2 * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[1].getName() + " it was super effective!", currentPokemon, enemyPokemon);
                    } else {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[1].getDamage() * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[3].getName(), currentPokemon, enemyPokemon);
                    }
                    if (currentPokemon.getHP() <= 0 && team.size() > 1) {
                        PrintGame("Your pokemon has fainted! Choose a different pokemon to be swapped in!", currentPokemon, enemyPokemon);
                        team.remove(currentPokemon);
                        PrintTeam(team);
                        String swap = Scan.nextLine().toLowerCase(Locale.ROOT);
                        for (int i = 0; i< team.size(); i++){
                            if (swap.equals(team.get(i).getName().toLowerCase(Locale.ROOT))){
                                currentPokemon = team.get(i);
                            }
                        }
                    }
                    else if (currentPokemon.getHP() <= 0 && team.size() == 1){
                        PrintGame("You have no Pokemon remaining, you lose!", currentPokemon, enemyPokemon);
                        System.exit(0);
                    }
                    break;
                case 3:
                    if (currentPokemon.getWeakness().contains(enemyPokemon.getType())) {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[2].getDamage() * 2 * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[2].getName() + " it was super effective!", currentPokemon, enemyPokemon);
                    } else {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[2].getDamage() * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[3].getName(), currentPokemon, enemyPokemon);
                    }
                    if (currentPokemon.getHP() <= 0 && team.size() > 1) {
                        PrintGame("Your pokemon has fainted! Choose a different pokemon to be swapped in!", currentPokemon, enemyPokemon);
                        team.remove(currentPokemon);
                        PrintTeam(team);
                        String swap = Scan.nextLine().toLowerCase(Locale.ROOT);
                        for (int i = 0; i< team.size(); i++){
                            if (swap.equals(team.get(i).getName().toLowerCase(Locale.ROOT))){
                                currentPokemon = team.get(i);
                            }
                        }
                    }
                    else if (currentPokemon.getHP() <= 0 && team.size() == 1){
                        PrintGame("You have no Pokemon remaining, you lose!", currentPokemon, enemyPokemon);
                        System.exit(0);
                    }
                    break;
                case 4:
                    if (currentPokemon.getWeakness().contains(enemyPokemon.getType())) {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[3].getDamage() * 2 * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[3].getName() + " it was super effective!", currentPokemon, enemyPokemon);
                    } else {
                        currentPokemon.setHP(currentPokemon.getHP() - (enemyPokemon.getMoveset()[3].getDamage() * ((currentPokemon.getLevel() + 20) / 20)));
                        PrintGame(enemyPokemon.getName() + " used " + enemyPokemon.getMoveset()[3].getName(), currentPokemon, enemyPokemon);
                    }
                    if (currentPokemon.getHP() <= 0 && team.size() > 0) {
                        PrintGame("Your pokemon has fainted! Choose a different pokemon to be swapped in!", currentPokemon, enemyPokemon);
                        team.remove(currentPokemon);
                        PrintTeam(team);
                        String swap = Scan.nextLine().toLowerCase(Locale.ROOT);
                        for (int i = 0; i< team.size(); i++){
                            if (swap.equals(team.get(i).getName().toLowerCase(Locale.ROOT))){
                                currentPokemon = team.get(i);
                            }
                        }
                    }
                    else if (currentPokemon.getHP() <= 0 && team.size() == 1){
                        PrintGame("You have no Pokemon remaining, you lose!", currentPokemon, enemyPokemon);
                        System.exit(0);
                    }
                    break;
                case 5:
                    //same logic for healing as the healing your own pokemon, this if block is for max heals
                    if (enemyPokemon.getHP() > enemyPokemon.getMaxHp() - 30) {
                        enemyPokemon.setHP(enemyPokemon.getMaxHp());
                        PrintGame("The enemy healed to max HP!", currentPokemon, enemyPokemon);
                    }
                    //heals the pokemon normally since it is not within max HP healing range
                    else {
                        enemyPokemon.setHP(enemyPokemon.getHP() + 30);
                        PrintGame("The enemy healed with a potion!", currentPokemon, enemyPokemon);
                    }
                    break;

                default:
                    //same logic for healing as the healing your own pokemon, this if block is for max heals
                    if (enemyPokemon.getHP() > enemyPokemon.getMaxHp() - 80) {
                        enemyPokemon.setHP(enemyPokemon.getMaxHp());
                        PrintGame("The enemy healed to max HP!", currentPokemon, enemyPokemon);
                    }
                    //heals the pokemon normally since it is not within max HP healing range
                    else {
                        enemyPokemon.setHP(enemyPokemon.getHP() + 80);
                        PrintGame("The enemy healed with a super potion!", currentPokemon, enemyPokemon);
                    }
                    break;
            }
        }
    }
    //PrintGame Method Here // if a pokemon's HP is negative, set it to zero

    public static void PrintGame(String text, Pokemon currentPokemon, Pokemon enemy){
        if (currentPokemon.getHP() < 0){
            currentPokemon.setHP(0);
        }
        if (enemy.getHP() < 0){
            enemy.setHP(0);
        }
        String enemyColor = "\u001B[1;" + enemy.getColour() + "m";
        String friendlyColor = "\u001B[1;" + currentPokemon.getColour() + "m";
        System.out.println("\u001B[4;33m" + "_____________________________________________________________________________" + ANSI_RESET);
        System.out.println(" " + enemy.getName() + " Lvl " + enemy.getLevel());
        System.out.println(" HP = " + enemy.getHP());
        System.out.println("                                     " + enemyColor + "O" + ANSI_RESET);
        System.out.println("                                    " + enemyColor + "-|-" + ANSI_RESET);
        System.out.println("                                    " + enemyColor + "/ \\" + ANSI_RESET);
        System.out.println();
        System.out.println("           " + friendlyColor + "O" + ANSI_RESET);
        System.out.println("          " + friendlyColor + "-|-" + ANSI_RESET + "              " + currentPokemon.getName() + " Lvl " + currentPokemon.getLevel());
        System.out.println("          " + friendlyColor + "/ \\" + "              " + ANSI_RESET + "HP = " + currentPokemon.getHP());
        System.out.println("\u001B[4;33m" + "_____________________________________________________________________________" + ANSI_RESET);
        System.out.println("|Att|   |Bag|   |Pok|   |Run|");
        System.out.println(text);
        System.out.println("\u001B[4;33m" + "_____________________________________________________________________________" + ANSI_RESET);
    }

    public static void PrintAttackMenu(Pokemon currentPokemon){
        System.out.println(" Attacks");
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println();
        System.out.println(" " + currentPokemon.getMoveset()[0].getName());
        System.out.println(" " + currentPokemon.getMoveset()[1].getName());
        System.out.println(" " + currentPokemon.getMoveset()[2].getName());
        System.out.println(" " + currentPokemon.getMoveset()[3].getName());
        System.out.println();
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println(" Choose an attack: ");
    }

    public static void PrintBag(Item[] bag){
        System.out.println(" Items");
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println();
        System.out.println(bag[0].getQuantity() + "x " + bag[0].getName());
        System.out.println(bag[1].getQuantity() + "x " + bag[1].getName());
        System.out.println(bag[2].getQuantity() + "x " + bag[2].getName());
        System.out.println();
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println("Choose an item to use:");
    }

    public static void PrintTeam(ArrayList<Pokemon> team){
        System.out.println(" Pokemon");
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println();
        for (int i = 0; i<team.size(); i++){
            System.out.println(" " + team.get(i).getName());
        }
        System.out.println();
        System.out.println("\u001B[4;33m" + "_______________________________" + ANSI_RESET);
        System.out.println(" Who would you like to swap to: ");
    }
    public static void attSwitch(Pokemon currentPokemon, Pokemon enemyPokemon, String attack){
        for (int i = 0; i<currentPokemon.getMoveset().length; i++){
            if (attack.equals(currentPokemon.getMoveset()[i].getName().toLowerCase(Locale.ROOT))){
                if (currentPokemon.getMoveset()[i].getName().toLowerCase(Locale.ROOT).equals("recover") && currentPokemon.getHP() < currentPokemon.getMaxHp()/2){
                    currentPokemon.setHP(currentPokemon.getHP() + (currentPokemon.getMaxHp()/2));
                    PrintGame(currentPokemon.getName() + " recovered!", currentPokemon, enemyPokemon);
                }
                else if (currentPokemon.getMoveset()[i].getName().toLowerCase(Locale.ROOT).equals("recover") && currentPokemon.getHP() > currentPokemon.getMaxHp()/2){
                    currentPokemon.setHP(currentPokemon.getMaxHp());
                    PrintGame(currentPokemon.getName() + " recovered!", currentPokemon, enemyPokemon);
                }
                if (enemyPokemon.getWeakness().contains(currentPokemon.getType()) && !currentPokemon.getMoveset()[i].getName().toLowerCase(Locale.ROOT).equals("recover")) {
                    enemyPokemon.setHP(enemyPokemon.getHP() - (currentPokemon.getMoveset()[i].getDamage() * 2 * ((currentPokemon.getLevel() + 20) / 20)));
                    PrintGame(currentPokemon.getName() + " used " + currentPokemon.getMoveset()[i].getName() + " it was super effective!", currentPokemon, enemyPokemon);
                }

                else if (!enemyPokemon.getWeakness().contains(currentPokemon.getType()) && !currentPokemon.getMoveset()[i].getName().toLowerCase(Locale.ROOT).equals("recover")) {
                    enemyPokemon.setHP(enemyPokemon.getHP() - (currentPokemon.getMoveset()[i].getDamage() * ((currentPokemon.getLevel() + 20) / 20)));
                    PrintGame(currentPokemon.getName() + " used " + currentPokemon.getMoveset()[i].getName(), currentPokemon, enemyPokemon);
                }

                if (enemyPokemon.getHP() <= 0){
                    PrintGame("The enemy " + enemyPokemon.getName() + " fainted, you win!", currentPokemon, enemyPokemon);
                    System.exit(0);
                }
            }
        }
    }
}
