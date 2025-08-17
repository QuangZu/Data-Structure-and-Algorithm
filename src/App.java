import services.BookManager;
import services.OrderManager;
import util.InputHelper;

public class App {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        BookManager bookManager = new BookManager("src/book.csv");

        boolean running = true;
        while (running) {
            System.out.println("\n===== ONLINE BOOKSTORE MENU =====");
            System.out.println("1. View all books");
            System.out.println("2. Search books");
            System.out.println("3. Add new order");
            System.out.println("4. View pending orders");
            System.out.println("5. Process next order");
            System.out.println("6. Search orders");
            System.out.println("7. Search book in an order");
            System.out.println("8. Sort books in an order");
            System.out.println("0. Exit");
            System.out.println("==================================");

            int choice = InputHelper.readInt("Select option: ");
            switch (choice) {
                case 1 -> bookManager.displayAllBooks();
                case 2 -> bookManager.searchBooks();
                case 3 -> orderManager.addOrder();
                case 4 -> orderManager.viewPendingOrders();
                case 5 -> orderManager.processOrder();
                case 6 -> orderManager.searchOrder();
                case 7 -> orderManager.searchBookInOrder();
                case 8 -> orderManager.sortOrderBooks();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
