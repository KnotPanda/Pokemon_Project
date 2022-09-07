public class Item {
    String name;
    int quantity;
    int healAmount;
    boolean isPokeball;

    public Item(String name, int quantity, int healAmount, boolean isPokeball){
        this.name = name;
        this.quantity = quantity;
        this.healAmount = healAmount;
        this.isPokeball = isPokeball;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public boolean isPokeball() {
        return isPokeball;
    }

    public void setPokeball(boolean pokeball) {
        isPokeball = pokeball;
    }
}