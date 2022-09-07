public class Pokemon {
    private String name;
    private int HP;
    private int maxHp;
    private int level;
    private String type;
    private String weakness;
    private Attack[] moveset;
    private int color;

    public Pokemon(String name, int HP, int maxHp, int level, String type, String weakness, Attack[] moveset, int color) {
        this.name = name;
        this.HP = HP;
        this.maxHp = maxHp;
        this.level = level;
        this.type = type;
        this.weakness = weakness;
        this.moveset = moveset;
        this.color = color;
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
}