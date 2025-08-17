package model;

import datastore.MyArray;

public class Order {
    private static int nextId = 1;
    private int orderId;
    private Book[] books;
    private int bookCount;
    private String status;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public Order(String customerName, String customerAddress, String customerPhone) {
        this.orderId = nextId++;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.books = new Book[10];
        this.bookCount = 0;
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Book[] getBooks() {
        Book[] copy = new Book[bookCount];
        System.arraycopy(books, 0, copy, 0, bookCount);
        return copy;
    }

    public void addBook(Book book) {
        if (bookCount == books.length) {
            expandCapacity();
        }
        books[bookCount++] = book;
    }

    public boolean removeBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title)) {
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--bookCount] = null;
                return true;
            }
        }
        return false;
    }

    public Book findBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    private void expandCapacity() {
        Book[] newBooks = new Book[books.length * 2];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        books = newBooks;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < bookCount; i++) {
            total += books[i].getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", books=" + MyArray.toString(books) +
                ", bookCount=" + getBookCount() +
                ", status='" + status + '\'' +
                ", totalPrice=" + calculateTotalPrice() +
                '}';

    }
}
