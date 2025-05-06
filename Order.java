public class Order {
    enum Type { BUY, SELL }

    private static int idCounter = 0;
    public final int id;
    public final Type type;
    public int quantity;
    public final double price;
    public final long timestamp;

    public Order(Type type, int quantity, double price) {
        this.id = ++idCounter;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = System.currentTimeMillis();
    }

    public String toString() {
        return String.format("Order[id=%d, type=%s, qty=%d, price=%.2f]", id, type, quantity, price);
    }
}
