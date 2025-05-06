import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderBook book = new OrderBook();

        while (true) {
            System.out.println("Enter order (BUY/SELL qty price), or 'exit':");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split("\\s+");
            try {
                Order.Type type = Order.Type.valueOf(parts[0].toUpperCase());
                int qty = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                Order order = new Order(type, qty, price);
                book.addOrder(order);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }

        sc.close();
    }
}
