import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Library library = new Library("Bayt AlHekma");
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            library.display();
            choice = readInt(input);
            input.nextLine();
            if(choice == 1){
                library.viewCatalogue();
            }
            else if(choice == 2){
                library.registerMember(input);
            }
            else if(choice == 3){
                library.addItem(input);
            }
            else if(choice == 4){
                library.borrowItem(input);
            }
            else if(choice == 5){
                library.returnItem(input);
            }
            else if(choice == 6){
                library.renewLoan(input);
            }
            else if (choice == 7) {
                System.out.print("Enter Item ID : ");
                int id = readInt(input);
                Item item = library.searchItemById(id);
                if(item != null){
                    item.display();
                }
                else{
                    System.out.println("Invalid ID ; ID not exists !");
                }
            }
            else if(choice == 8){
                library.displayItemsByStatus(input);
            }
            else if(choice == 9){
                library.payOutstandingFine(input);
            }
            else if(choice == 10){
                library.displayMembers();
            }
            else if(choice == 11){
                library.libraryReport();
            }
            else if(choice == 12){
                library.markLost(input);
            }
            else if(choice == 0){
                System.out.println("Good Bye !");
            }
            else{
                System.out.println("Invalid choice ; Please enter between 1 and 12 OR 0 for Exit");
            }
        }while (choice != 0);
    }
    public static int readInt(Scanner input){
        while(true){
            try {
                return input.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid input ; Please enter a integer number.");
                input.nextLine();
            }
        }
    }
}
