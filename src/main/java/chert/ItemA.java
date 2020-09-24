package chert;

public class ItemA implements Item {
    public int id;
    public String itemName;
    public User owner;

    public ItemA(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
