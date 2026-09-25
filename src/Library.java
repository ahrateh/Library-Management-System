import java.util.Scanner;

public class Library {
    private final String name;
    private Item[] items = new Item[20];
    private Member[] members = new Member[20];
    int indexItem = 0;
    int indexMember = 0;
    Scanner input = new Scanner(System.in);

    Library(String name) {
        this.name = name;
    }

    public void display(){
        System.out.println("-----------------------------------------------");
        System.out.println("\t\t\t\t " + this.name);
        System.out.println("-----------------------------------------------");
        System.out.println("1. View catalogue");
        System.out.println("2. Register member");
        System.out.println("3. Add Item");
        System.out.println("4. Borrow item");
        System.out.println("5. Return item");
        System.out.println("6. Renew loan");
        System.out.println("7. Search item by ID");
        System.out.println("8. View items by status");
        System.out.println("9. Pay outstanding fines");
        System.out.println("10. View all members");
        System.out.println("11. Library report");
        System.out.println("12. Mark item as lost");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------------------");
    }

    public void viewCatalogue(){
        if(indexItem == 0){
            System.out.println("No items available.");
            return;
        }
        for(int i = 0; i < indexItem; i++){
            if(i > 0){
                System.out.println("-----------------------------------------------");
            }
            items[i].display();
        }
    }

    public void addItem(Scanner input){
        if(indexItem == items.length){
            System.out.println("ERROR ; Full Register");
            return;
        }
        System.out.println("Enter Type Of Item : ( 1. Book ) OR ( 2. Magazine )  OR ( 3. DVD )");
        int choiceType = readChoice(input);
        input.nextLine();
        if(choiceType == 1){
            System.out.print("Enter Book Title : ");
            String bookTitle = input.nextLine();
            System.out.print("Enter Book Author : ");
            String bookAuthor = input.nextLine();
            System.out.print("Enter Page Count : ");
            int pageCount = readInt(input);
            System.out.println("Enter Book Status : ( 1. Available )  OR ( 2. On Loan ) OR ( 3. Lost )");
            int choiceStatus =  readChoice(input);
            if(choiceStatus == 1){
                items[indexItem++] = new Book(bookTitle,ItemStatus.AVAILABLE,bookAuthor,pageCount);
            }
            else if(choiceStatus == 2){
                System.out.print("Enter Borrower ID : ");
                int borrowerID = readInt(input);
                Member borrower = searchMemberById(borrowerID);
                if(borrower == null){
                    System.out.println("Invalid ID ; ID not exists !");
                    return;
                }
                if(borrower.getNumOfItems() < 3){
                    if (borrower.getBalanceOwed() < 100){
                        items[indexItem++] = new Book(bookTitle,borrowerID,ItemStatus.ON_LOAN,bookAuthor,pageCount);
                        borrower.setNumOfItems(borrower.getNumOfItems() + 1);
                    }
                    else{
                        System.out.println("ERROR ; Balance Owed equal 100 !");
                    }
                }
                else{
                    System.out.println("ERROR ; Member already holds 3 items !");
                }
            }
            else{
                items[indexItem++] = new Book(bookTitle,ItemStatus.LOST,bookAuthor,pageCount);
            }
        }
        else if(choiceType == 2){
            System.out.print("Enter Magazine Title : ");
            String bookTitle = input.nextLine();
            System.out.print("Enter Magazine Issue Number : ");
            int issueNumber = readInt(input);
            System.out.println("Enter Magazine Status : ( 1. Available )  OR ( 2. On Loan ) OR ( 3. Lost )");
            int choiceStatus = readChoice(input);
            if(choiceStatus == 1){
                items[indexItem++] = new Magazine(bookTitle,ItemStatus.AVAILABLE,issueNumber);
            }
            else if(choiceStatus == 2){
                System.out.print("Enter Borrower ID : ");
                int borrowerID = readInt(input);
                Member borrower = searchMemberById(borrowerID);
                if(borrower == null){
                    System.out.println("Invalid ID ; ID not exists !");
                    return;
                }
                if(borrower.getNumOfItems() < 3){
                    if (borrower.getBalanceOwed() < 100){
                        items[indexItem++] = new Magazine(bookTitle,borrowerID,ItemStatus.ON_LOAN,issueNumber);
                        borrower.setNumOfItems(borrower.getNumOfItems() + 1);
                    }
                    else{
                        System.out.println("ERROR ; Balance Owed equal 100 !");
                    }
                }
                else{
                    System.out.println("ERROR ; Member already holds 3 items !");
                }
            }
            else{
                items[indexItem++] = new Magazine(bookTitle,ItemStatus.LOST,issueNumber);
            }
        }
        else{
            System.out.print("Enter DVD Title : ");
            String bookTitle = input.nextLine();
            System.out.print("Enter DVD Runtime : ");
            double runtime = readDouble(input);
            System.out.println("Enter DVD Status : ( 1. Available )  OR ( 2. On Loan ) OR ( 3. Lost )");
            int choiceStatus = readChoice(input);
            if(choiceStatus == 1){
                items[indexItem++] = new DVD(bookTitle,ItemStatus.AVAILABLE,runtime);
            }
            else if(choiceStatus == 2){
                System.out.print("Enter Borrower ID : ");
                int borrowerID = readInt(input);
                Member borrower = searchMemberById(borrowerID);
                if(borrower == null){
                    System.out.println("Invalid ID ; ID not exists !");
                    return;
                }
                if(borrower.getNumOfItems() < 3){
                    if (borrower.getBalanceOwed() < 100){
                        items[indexItem++] = new DVD(bookTitle,borrowerID,ItemStatus.ON_LOAN,runtime);
                        borrower.setNumOfItems(borrower.getNumOfItems() + 1);
                    }
                    else{
                        System.out.println("ERROR ; Balance Owed equal 100 !");
                    }
                }
                else{
                    System.out.println("ERROR ; Member already holds 3 items !");
                }
            }
            else{
                items[indexItem++] = new DVD(bookTitle,ItemStatus.LOST,runtime);
            }
        }
    }

    public void registerMember(Scanner input){
        if(indexMember == members.length){
            System.out.println("ERROR ; Full Register");
            return;
        }
        System.out.println("Member : ( 1. New ) OR ( 2. Old ) ");
        int choiceType;
        do {
            choiceType = readInt(input);
            if(choiceType < 1 || choiceType > 2){
                System.out.println("Invalid Choice ; Please Choose 1 OR 2");
            }
        }while(choiceType < 1 || choiceType > 2);
        input.nextLine();
        if(choiceType == 1){
            System.out.print("Enter Name : ");
            String name = input.nextLine();
            System.out.print("Enter ID : ");
            int id = readInt(input);
            Member member = searchMemberById(id);
            if(member != null){
                System.out.println("ERROR ; Member ID already exists!");
                return;
            }
            System.out.println("Enter Membership Type : ( 1. Basic ) OR ( 2. Silver ) OR ( 3. Gold )");
            int memberType =  readChoice(input);
            if(memberType == 1){
                members[indexMember++] = new Member(name,id,MembershipType.BASIC,0);
            }
            else if(memberType == 2){
                members[indexMember++] = new Member(name,id,MembershipType.SILVER,0);
            }
            else{
                members[indexMember++] = new Member(name,id,MembershipType.GOLD, 0);
            }
        }
        else{
            System.out.print("Enter Name : ");
            String name = input.nextLine();
            System.out.print("Enter ID : ");
            int id = readInt(input);
            Member member = searchMemberById(id);
            if(member != null){
                System.out.println("ERROR ; Member ID already exists!");
                return;
            }
            System.out.print("Enter Balance Owed : ");
            double balanceOwed;
            do {
                balanceOwed = readDouble(input);
                if(balanceOwed <= 0){
                    System.out.println("Invalid Balance Owed ; Please enter a positive number");
                }
            }while (balanceOwed <= 0);
            System.out.println("Enter Membership Type : ( 1. Basic ) OR ( 2. Silver ) OR ( 3. Gold )");
            int memberType =  readChoice(input);
            if(memberType == 1){
                members[indexMember++] = new Member(name,id,MembershipType.BASIC,balanceOwed);
            }
            else if(memberType == 2){
                members[indexMember++] = new Member(name,id,MembershipType.SILVER,balanceOwed);
            }
            else{
                members[indexMember++] = new Member(name,id,MembershipType.GOLD, balanceOwed);
            }
        }
    }

    public void borrowItem(Scanner input){
        System.out.print("Enter Item ID : ");
        int itemId = readInt(input);
        Item  item = searchItemById(itemId);
        if (item == null){
            System.out.println("Invalid ID ; ID not exists !");
            return;
        }
        if(item.getStatus() == ItemStatus.AVAILABLE){
            System.out.print("Enter Member ID : ");
            int memberId = readInt(input);
            Member member = searchMemberById(memberId);
            if (member == null){
                System.out.println("Invalid ID ; ID not exists !");
                return;
            }
            if (member.getNumOfItems() < 3){
                if (searchMemberById(memberId).getBalanceOwed() < 100){
                    item.setMemberId(memberId);
                    item.setItemStatus(ItemStatus.ON_LOAN);
                    member.setNumOfItems(member.getNumOfItems() + 1);
                    System.out.println("Successfully Borrowed Item");
                }
                else {
                    System.out.println("ERROR ; Balance Owed equal 100 !");

                }
            }
            else {
                System.out.println("ERROR ; Member already holds 3 items !");
            }
        }
        else if(item.getStatus() == ItemStatus.LOST){
            System.out.println("ERROR ; Item is Lost");
        }
        else{
            System.out.println("ERROR ; Item is not available");
        }
    }

    public void returnItem(Scanner input){
        System.out.print("Enter Item ID : ");
        int itemId = readInt(input);
        Item item = searchItemById(itemId);
        if (item == null){
            System.out.println("Invalid ID ; ID not exists !");
            return;
        }
        if(item.getStatus() == ItemStatus.AVAILABLE){
            System.out.println("ERROR ; Item is Available");
        }
        else if(item.getStatus() == ItemStatus.LOST){
            System.out.println("ERROR ; Item is Lost");
        }
        else{
            Member member = searchMemberById(item.getMemberId());
            System.out.print("Enter overdue Days : ");
            int overdueDays;
            do {
                overdueDays = readInt(input);
                if(overdueDays < 0){
                    System.out.println("Invalid Overdue Days ; Please Enter Positive Number");
                }
            }while (overdueDays < 0);
            if (overdueDays == 0){
                System.out.println("Item Return ; Not Fine It is return on time");
                member.setNumOfItems(member.getNumOfItems() - 1);
                item.setItemStatus(ItemStatus.AVAILABLE);
                item.setMemberId(0);
                item.setNumRenewalsUsed(0);
            }
            else{
                member.setBalanceOwed( member.getBalanceOwed() + ( item.fine(overdueDays) - (item.fine(overdueDays) * member.getMembershipType().getWaiver()) ) );
                System.out.println("-----------------------------------------------");
                System.out.println("Item ID : " + itemId);
                System.out.println("Overdue Days : " + overdueDays);
                System.out.println("Fine : " + item.fine(overdueDays) + " EGP");
                System.out.println("Membership Waiver : " + member.getMembershipType().getWaiver() * 100 + "%");
                System.out.println("Total Fine after Waiver : " + ( item.fine(overdueDays) - (item.fine(overdueDays) * member.getMembershipType().getWaiver()) ) + "EGP");
                member.setNumOfItems(member.getNumOfItems() - 1);
                item.setItemStatus(ItemStatus.AVAILABLE);
                item.setMemberId(0);
                item.setNumRenewalsUsed(0);
            }
        }
    }

    public void renewLoan(Scanner input){
        System.out.print("Enter Item ID : ");
        int itemId = readInt(input);
        if(searchItemById(itemId) == null){
            return;
        }
        if(searchItemById(itemId).getStatus() == ItemStatus.AVAILABLE){
            System.out.println("ERROR ; Item is Not on Loan");
        }
        else if(searchItemById(itemId).getStatus() == ItemStatus.LOST){
            System.out.println("ERROR ; Item is Lost");
        }
        else{
            if(searchItemById(itemId) instanceof Book){
                ((Book) searchItemById(itemId)).renewLoan();
            }
            else if (searchItemById(itemId) instanceof Magazine) {
                ((Magazine) searchItemById(itemId)).renewLoan();
            }
            else{
                System.out.println("ERROR ; DVD can not renew loan");
            }
        }
    }

    public void displayItemsByStatus(Scanner input){
        System.out.println("Enter Status : ( 1. Available )  OR ( 2. On Loan ) OR ( 3. Lost )");
        int status = readChoice(input);
        if(status == 1){
            for(int i = 0; i < indexItem; i++){
                if (items[i].getStatus() == ItemStatus.AVAILABLE){
                    items[i].display();
                }
            }
        }
        else if(status == 2){
            for(int i = 0; i < indexItem; i++){
                if (items[i].getStatus() == ItemStatus.ON_LOAN){
                    items[i].display();
                }
            }
        }
        else {
            for(int i = 0; i < indexItem; i++){
                if (items[i].getStatus() == ItemStatus.LOST){
                    items[i].display();
                }
            }
        }
    }

    public void payOutstandingFine(Scanner input){
        System.out.print("Enter Member ID : ");
        int memberId = readInt(input);
        Member member = searchMemberById(memberId);
        if(member == null){
            System.out.println("Invalid ID ; ID not exists !");
            return;
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Member Name : " + member.getName());
        System.out.println("Balance Owed : " + member.getBalanceOwed());
        System.out.println("-----------------------------------------------");
        System.out.println("Amount you to pay : ");
        double amount;
        do {
            amount = readDouble(input);
            if(amount <= 0){
                System.out.println("Invalid Amount ; Please Enter Positive Number");
            }
        }while (amount <= 0);
        if(member.getBalanceOwed() < amount){
            System.out.println("Invalid Paid ; Amount more than Owed");
            return;
        }
        member.setBalanceOwed(member.getBalanceOwed() - amount);
        System.out.println("Successfully Paid : " + amount + " - Balance Owed : " + member.getBalanceOwed());

    }

    public void displayMembers(){
        for(int i = 0; i < indexMember; i++){
            if(i > 0){
                System.out.println("-----------------------------------------------");
            }
            members[i].display();
        }
    }

    public void libraryReport(){
        System.out.println("Catalogue Size : 3");
        System.out.println("Items ever added : " + (Item.getCount() - 2000) );
        int countItemOnLoan = 0;
        for(int i = 0; i < indexItem; i++){
            if(items[i].getStatus() == ItemStatus.ON_LOAN){
                countItemOnLoan++;
            }
        }
        System.out.println("Items on loan : " + countItemOnLoan );
        double loanRate =  ((double) countItemOnLoan / (Item.getCount() - 2000)) * 100;
        System.out.println("Loan Rate : " + loanRate + "%" );
        double totalOutstanding = 0;
        for(int i = 0; i < indexMember; i++){
            totalOutstanding += members[i].getBalanceOwed();
        }
        System.out.println("Total Outstanding : " +  totalOutstanding + "EGP");
        double projectedFines = 0;
        for(int i = 0; i < indexItem; i++){
            if(items[i].getStatus() == ItemStatus.ON_LOAN){
                projectedFines += items[i].fine(5);
            }
        }
        System.out.println("Projected fines for a 5-day overdue : " + projectedFines);

    }

    public void markLost(Scanner input){
        System.out.print("Enter item ID : ");
        int itemId = readInt(input);
        Item item = searchItemById(itemId);
        if(item == null){
            System.out.println("ERROR ID ; ID not exists !");
            return;
        }
        if(item.getStatus() == ItemStatus.ON_LOAN){
            System.out.println("ERROR ; Item already On Loan can not mark Lost");
        }
        else if(item.getStatus() == ItemStatus.LOST){
            System.out.println("ERROR ; Item already Lost");
        }
        else{
            item.setItemStatus(ItemStatus.LOST);
            System.out.println("Successfully Item ID " + item.getId()  + " Marked Lost");
        }

    }

    public Item searchItemById(int id){
        for(int i = 0; i < indexItem; i++){
            if(items[i].getId() == id){
                return items[i];
            }
        }
        return null;
    }

    public Member searchMemberById(int id){
        for(int i = 0; i < indexMember; i++){
            if(members[i].getId() == id){
                return  members[i];
            }
        }
        return null;
    }

    public int readInt(Scanner input){
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

    public double readDouble(Scanner input){
        while(true){
            try {
                return input.nextDouble();
            }
            catch(Exception e){
                System.out.println("Invalid input ; Please enter a number.");
                input.nextLine();
            }
        }
    }

    public int readChoice(Scanner input){
        int choice;
        do {
            choice = readInt(input);
            if(choice < 1 || choice > 3){
                System.out.println("Invalid Choice ; Please Choose 1 OR 2 OR 3");
            }
        }while(choice < 1 || choice > 3);
        return choice;
    }


}
