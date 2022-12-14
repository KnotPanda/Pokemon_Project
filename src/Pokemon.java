import java.util.Random;

public class Pokemon {
    private String name;
    private int HP;
    private int maxHp;
    private int level;
    private String type;
    private String weakness;
    private Attack[] moveset;
    private int color;
    private String status;

    public Pokemon(String name, int HP, int maxHp, int level, String type, String weakness, Attack[] moveset, int color, String status) {
        this.name = name;
        this.HP = HP;
        this.maxHp = maxHp;
        this.level = level;
        this.type = type;
        this.weakness = weakness;
        this.moveset = moveset;
        this.color = color;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public Attack[] getMoveset() {
        return moveset;
    }

    public void setMoveset(Attack[] moveset) {
        this.moveset = moveset;
    }

    public int getColour() {
        return color;
    }

    public void setColour(int color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void damageCalc(Pokemon pokemon, Attack move){
        if(this.getWeakness().contains(pokemon.getType())) { //Takes super effective moves into consideration
            //damage calculation formula incorporating pokemon level
            this.setHP(this.getHP() - (move.getDamage() * 2 * ((this.getLevel() + 20) / 20)));
            System.out.println("The move was super effective!");
        }
        else{ //non-super effective moves
            this.setHP(this.getHP() - (move.getDamage() * ((this.getLevel() + 20) / 20)));
        }
    }


    public void statusEffects(){
        switch(this.status){
            case "Burn": //If pokemon has burn, 5% of max HP is lost per turn
                this.HP -= this.maxHp*.05;
                System.out.println(this.getName() + " has been burned");
                break;

            case "Confusion":
                Random rand = new Random();
                int num = rand.nextInt(101);
                if(num>51){ //50% chance for confusion to trigger
                    //Damages itself for 10% of the pokemon's current health
                    this.HP -= this.HP*.10;
                    System.out.println(this.getName() + " is confused and its own attack has damaged itself a little");
                }
                break;
        }
    }
}