import java.io.FileWriter;
import java.io.IOException;

public class JavaTasks2 {
    // Making custom exception for product out of stock
    static class OutOfStockException extends Exception {
        public OutOfStockException(String message) {
            super(message);
        }
    }


    // Shared Inventory Class
    public static class sharedInventory {
        private int stock;

        public sharedInventory(int stock) {
            this.stock = stock;
        }
//  making update stock synchronized for simultaneous order
        public synchronized void updateStock(int quantity) throws OutOfStockException {
            if (stock >= quantity) {
                System.out.println(Thread.currentThread().getName() + " is processing order, current stock: " + stock);
                stock = stock - quantity;
                System.out.println(Thread.currentThread().getName() + " completed order, remaining stock: " + stock);
            } else {
                System.out.println(Thread.currentThread().getName() + " tried to order, but stock is not available.");
                throw new OutOfStockException("product is out of stock");
            }
        }
    }

    // Order Processor with Simulated File I/O
    public static class OrderProcessor implements Runnable {

        private String OrderId;
        private sharedInventory inventory;

        public OrderProcessor(String OrderId, sharedInventory inventory) {
            this.OrderId = OrderId;
            this.inventory = inventory;
        }

        @Override
        public void run() {
            System.out.println("Processing Order " + OrderId + " from " + Thread.currentThread().getName());

            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }

            // Simulate file writing (I/O) wrapped in try-catch-finally
            FileWriter writer = null;
            try {
                // Simulate failure conditionally
                if (OrderId.equals("102")) {
                    throw new IOException("Simulated I/O failure for order " + OrderId);
                }

                writer = new FileWriter("order_log.txt", true);
                writer.write("Order " + OrderId + " processed by " + Thread.currentThread().getName() + "\n");
            } catch (IOException e) {
                System.err.println("I/O error while processing order " + OrderId + ": " + e.getMessage());
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.err.println("Failed to close writer for order " + OrderId);
                    }
                }
            }

            // Always attempt to update stock
            try {
                inventory.updateStock(1);
            } catch (OutOfStockException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Completed order " + OrderId + " from " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        sharedInventory inventory = new sharedInventory(5);

        // Create threads for each order
        Thread t1 = new Thread(new OrderProcessor("101", inventory));
        Thread t2 = new Thread(new OrderProcessor("102", inventory)); // This one simulates I/O failure
        Thread t3 = new Thread(new OrderProcessor("103", inventory));

//        int totalOrders = 100;
//        Thread[] threads = new Thread[totalOrders]; // Create an array to hold all threads
//
//        // Start threads for each order
//        for (int i = 1; i <= totalOrders; i++) {
//            String orderId = String.valueOf(i);
//            threads[i - 1] = new Thread(new OrderProcessor(orderId, inventory)); // Create new thread for each order
//            threads[i - 1].start(); // Start the thread immediately
//        }


        // Start threads in parallel
        t1.start();
        t2.start();
        t3.start();
    }
}

