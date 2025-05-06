import java.util.*;

public class OrderBook {
    private PriorityQueue<Order> buyOrders = new PriorityQueue<>(
            (o1, o2) -> o1.price == o2.price ?
                    Long.compare(o1.timestamp, o2.timestamp) :
                    Double.compare(o2.price, o1.price)  // highest price first
    );
    private PriorityQueue<Order> sellOrders = new PriorityQueue<>(
            (o1, o2) -> o1.price == o2.price ?
                    Long.compare(o1.timestamp, o2.timestamp) :
                    Double.compare(o1.price, o2.price)  // lowest price first
    );

    public void addOrder(Order order) {
        if (order.type == Order.Type.BUY) {
            matchOrder(order, sellOrders, buyOrders);
        } else {
            matchOrder(order, buyOrders, sellOrders);
        }
    }

    private void matchOrder(Order incoming, PriorityQueue<Order> opposite, PriorityQueue<Order> sameSide) {
        while (!opposite.isEmpty()) {
            Order top = opposite.peek();
            boolean match = incoming.type == Order.Type.BUY
                    ? incoming.price >= top.price
                    : incoming.price <= top.price;

            if (!match) break;

            System.out.printf("TRADE: %d units @ %.2f (Buy #%d <-> Sell #%d)\n",
                    Math.min(incoming.quantity, top.quantity),
                    top.price,
                    incoming.type == Order.Type.BUY ? incoming.id : top.id,
                    incoming.type == Order.Type.SELL ? incoming.id : top.id
            );

            int tradedQty = Math.min(incoming.quantity, top.quantity);
            incoming.quantity -= tradedQty;
            top.quantity -= tradedQty;

            if (top.quantity == 0) opposite.poll(); // remove matched order
            if (incoming.quantity == 0) return; // fully matched
        }
        sameSide.add(incoming);
    }
}
