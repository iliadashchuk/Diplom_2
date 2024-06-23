package stellarburgers.models;

public class Order {
    private String[] ingredients;
    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }
    public Order() {}
    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
