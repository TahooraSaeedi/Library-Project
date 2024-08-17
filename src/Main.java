
import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("please enter number your role? 1.manager 2.librarian 3.book reader");
        Scanner sc = new Scanner(System.in);
        int role= sc.nextInt();
        System.out.println("please enter your name:");
        String userName= sc.nextLine();
        System.out.println("please enter your password:");
        String pass= sc.nextLine();
        switch (role) {
            case 1:
                Manager man=new Manager();
                boolean result1=man.verify(userName,pass);
                if(result1) {
                    System.out.println("please enter number of want to do? 1.see books 2.insert new book 3.remove a book");
                    int want=sc.nextInt();
                    Library lib1=new Library();
                    switch (want){
                        case 1:
                            lib1.getbooks();
                            break;
                        case 2:
                            System.out.println("please enter name of new book:");
                            String bookName= sc.nextLine();
                            System.out.println("please enter author of new book:");
                            String authorName= sc.nextLine();
                            System.out.println("please enter year of publication new book:");
                            String publication= sc.nextLine();
                            System.out.println("how many of these books do you have? ");
                            int count= sc.nextInt();
                            man.insertNewBook(bookName,authorName,publication,count);
                            break;
                        case 3:
                            System.out.println("what is name of book do you want the remove it? ");
                            String rmBook= sc.nextLine();
                            lib1.findBook(rmBook);
                            System.out.println("are you sure? yes/no ");
                            String sure= sc.nextLine();
                            if(sure.equals("yes")) {
                                man.removeBook(rmBook);
                            }
                            break;
                    }
                }
                break;
            case 2:
                Librarian srlib=new Librarian();
                boolean result2=srlib.verify(userName,pass);
                if(result2) {
                    System.out.println("please enter number of want to do? 1.See the book reservation request list 2.Confirm or reject the reservation request");
                    int want=sc.nextInt();
                    switch (want){
                        case 1:
                            srlib.seeReq();
                            break;
                        case 2:
                            srlib.seeReq();
                            System.out.println("which request?(enter number of request)");
                            int reqId= sc.nextInt();
                            System.out.println("which one? 1.confirm 2.reject");
                            int reqState= sc.nextInt();
                            srlib.resOfReq(reqId,reqState);
                            break;
                    }
                }
                break;
            case 3:
                BookReader usr=new BookReader();
                boolean result3=usr.verify(userName,pass);
                if(result3) {
                    System.out.println("please enter number of want to do? 1.See books 2.reservation request 3.see state of your request");
                    int want=sc.nextInt();
                    switch (want){
                        case 1:
                            Library lib2=new Library();
                            lib2.getbooks();
                            break;
                        case 2:
                            Library lib3=new Library();
                            System.out.println("which book do you want it?");
                            String bookName= sc.nextLine();
                            lib3.findBook(bookName);
                            System.out.println("Are you sure? do you want it? yes/no");
                            String sure= sc.nextLine();
                            if(sure.equals("yes")) {
                                usr.newReq(bookName);
                            }
                            break;
                        case 3:
                            usr.stateOfReq(userName);
                            break;
                    }
                }
        }
    }
}