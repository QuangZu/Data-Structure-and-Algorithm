package services;

import algorithms.searches.BinarySearch;
import algorithms.searches.LinearSearch;
import algorithms.sorts.InsertionSort;
import algorithms.sorts.MergeSort;
import algorithms.sorts.QuickSort;
import datastore.MyQueue;
import model.Book;
import model.Order;
import util.InputHelper;

public class OrderManager {
    private MyQueue<Order> orderQueue;
    private BookManager bookManager;

    public OrderManager() {
        orderQueue = new MyQueue<>(100);
        bookManager = new BookManager("src/book.csv");
    }

    public void addOrder() {
        System.out.println("\n--- Add New Order ---");
        String customerName = InputHelper.readString("Enter customer name: ");
        String customerAddress = InputHelper.readString("Enter customer address: ");
        String customerPhone = InputHelper.readString("Enter customer phone: ");

        Order newOrder = new Order(customerName, customerAddress, customerPhone);

        boolean addingBooks = true;
        while (addingBooks) {
            System.out.println("\nAdd books to order:");
            String title = InputHelper.readString("Enter book title: ");
            String author = InputHelper.readString("Enter book author: ");
            
            // Search for the book in CSV data
            Book foundBook = bookManager.findBookByTitleAndAuthor(title, author);
            
            if (foundBook != null) {
                System.out.println("Book found: " + foundBook);
                
                Book orderBook = new Book(foundBook.getTitle(), foundBook.getAuthor(),foundBook.getPrice());
                newOrder.addBook(orderBook);
                
                System.out.println("Book added to order successfully!");
            } else {
                System.out.println("Book not found in our catalog.");
                System.out.println("Available options:");
                System.out.println("1. Try different title/author");
                System.out.println("2. View all books");
                System.out.println("3. Search books");
                
                int choice = InputHelper.readInt("Select option (or 0 to skip): ");
                switch (choice) {
                    case 1: continue;
                    case 2: {
                        bookManager.displayAllBooks();
                        continue;
                    }
                    case 3: {
                        bookManager.searchBooks();
                        continue;
                    }
                    default: System.out.println("Skipping this book.");
                }
            }

            addingBooks = InputHelper.readYesNo("Add another book?");
        }

        if (newOrder.getBookCount() > 0) {
            orderQueue.enqueue(newOrder);
            System.out.println("Order #" + newOrder.getOrderId() + " added successfully!");
            System.out.println("Total price: $" + String.format("%.2f", newOrder.calculateTotalPrice()));
        } else {
            System.out.println("Order cancelled - no books added.");
        }
    }

    public void viewPendingOrders() {
        System.out.println("--- Pending Orders ---");

        if (orderQueue.size() == 0) {
            System.err.println("No pending orders");
            return;
        }

        Order[] orders = snapshotQueue();

        System.err.println("Total pending orders: " + orders.length);
        System.err.println("-".repeat(80));

        for (Order order : orders) {
            if ("Pending".equals(order.getStatus())) {
                System.err.println(order);
                System.err.println("-".repeat(80));
            }
        }
    }

    public void processOrder() {
        System.err.println("\n--- Process Next Order ---");

        if (orderQueue.size() == 0) {
            System.err.println("No orders to process.");
            return;
        }

        Order nextOrder = orderQueue.dequeue();
        nextOrder.setStatus("Processed");

        System.out.println("Processing order #" + nextOrder.getOrderId());
        System.out.println("Customer: " + nextOrder.getCustomerName());
        System.out.println("Address: " + nextOrder.getCustomerAddress());
        System.out.println("Phone: " + nextOrder.getCustomerPhone());
        System.out.println("Books in order: " + nextOrder.getBookCount());
        System.out.println("Total amount: $" + String.format("%.2f", nextOrder.calculateTotalPrice()));
        System.out.println("Order status changed to: " + nextOrder.getStatus());
        System.out.println("Order #" + nextOrder.getOrderId() + " has been processed successfully!");
    }

    private Order[] snapshotQueue() {
        int size = orderQueue.size();
        Order[] tempOrders = new Order[size];
        for (int i = 0; i < size; i++) {
            tempOrders[i] = orderQueue.dequeue();
        }
        for (Order o : tempOrders) {
            orderQueue.enqueue(o);
        }
        return tempOrders;
    }

    public void searchOrder() {
        String target = InputHelper.readString("Enter customer name to search: ");
        Order[] orders = snapshotQueue();

        String[] names = java.util.Arrays.stream(orders)
                .map(Order::getCustomerName)
                .toArray(String[]::new);

        int index = LinearSearch.search(names, target);

        if (index >= 0) {
            System.out.println("Order found: " + orders[index]);
        } else {
            System.out.println("Order not found.");
        }
    }

    public void searchBookInOrder() {
        int orderId = InputHelper.readInt("Enter order ID: ");
        String title = InputHelper.readString("Enter book title: ");

        Order[] orders = snapshotQueue();
        Order targetOrder = null;
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                targetOrder = o;
                break;
            }
        }

        if (targetOrder == null || targetOrder.getBookCount() == 0) {
            System.out.println("Order not found or has no books.");
            return;
        }

        Book[] books = targetOrder.getBooks();

        MergeSort.sort(books, true);

        String[] titles = java.util.Arrays.stream(books)
                .map(Book::getTitle)
                .toArray(String[]::new);

        int index = BinarySearch.search(titles, title);

        if (index >= 0) {
            System.out.println("Book found: " + books[index]);
        } else {
            System.out.println("Book not found in this order.");
        }
    }
    public void sortOrderBooks() {
        int id = InputHelper.readInt("Enter order ID to sort books: ");

        // Extract orders from queue
        int size = orderQueue.size();
        Order targetOrder = null;
        Order[] tempOrders = new Order[size];

        for (int i = 0; i < size; i++) {
            Order o = orderQueue.dequeue();
            if (o.getOrderId() == id) {
                targetOrder = o;
            }
            tempOrders[i] = o;
        }
        
        for (Order o : tempOrders) {
            orderQueue.enqueue(o);
        }

        if (targetOrder == null) {
            System.out.println("Order not found.");
            return;
        }
        if (targetOrder.getBookCount() == 0) {
            System.out.println("This order has no books to sort.");
            return;
        }

        boolean ascending = InputHelper.readYesNo("Sort ascending?");

        Book[] books = targetOrder.getBooks();
        int n = targetOrder.getBookCount();

        if (n <= 10) {
            System.out.println("Sorting with InsertionSort (O(n^2), Stable, In-place)");
            InsertionSort.sort(books, ascending);
        } else if (n <= 50) {
            System.out.println("Sorting with QuickSort (O(n log n), Unstable, In-place)");
            QuickSort.sort(books, ascending);
        } else {
            System.out.println("Sorting with MergeSort (O(n log n), Stable, Not in-place)");
            MergeSort.sort(books, ascending);
        }

        System.out.println("Books after sorting:");
        for (Book b : books) {
            System.out.println(" - " + b);
        }
    }

}
