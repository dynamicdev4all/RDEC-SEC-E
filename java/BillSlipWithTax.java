import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    class Item {
        String name;
        double price;
    
        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
    
    public class BillSlipWithTax {
    
        private List<Item> items;
        private static final double TAX_RATE = 0.10; // 10% tax
    
        public BillSlipWithTax() {
            items = new ArrayList<>();
        }
    
        public void addItem(String name, double price) {
            items.add(new Item(name, price));
        }
    
        public void printBill() {
            System.out.println("********** Bill Slip **********");
            System.out.printf("%-20s %10s\n", "Item", "Price");
            System.out.println("-------------------------------");
            
            double total = 0;
            for (Item item : items) {
                System.out.printf("%-20s $%9.2f\n", item.name, item.price);
                total += item.price;
            }
    
            double tax = total * TAX_RATE;
            double totalWithTax = total + tax;
            
            System.out.println("-------------------------------");
            System.out.printf("%-20s $%9.2f\n", "Subtotal", total);
            System.out.printf("%-20s $%9.2f\n", "Tax (10%)", tax);
            System.out.printf("%-20s $%9.2f\n", "Total", totalWithTax);
            System.out.println("********************************");
        }
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            BillSlipWithTax billSlip = new BillSlipWithTax();
    
            String continueAdding;
            do {
                System.out.print("Enter item name: ");
                String name = scanner.nextLine();
    
                System.out.print("Enter item price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
    
                billSlip.addItem(name, price);
    
                System.out.print("Do you want to add another item? (yes/no): ");
                continueAdding = scanner.nextLine();
            } while (continueAdding.equalsIgnoreCase("yes"));
    
            billSlip.printBill();
            scanner.close();
        }
    }
    
    
}
