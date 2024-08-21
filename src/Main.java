import java.util.*;
public class Main {

    static Scanner sc;

    public static void caseRoleManager(String userName,String password) {
        Manager man=new Manager(userName,password);
        if(User.login(userName,password) != null) {
            boolean runOn=false;
            do{
                System.out.println("please enter number of want to do? 1.see books 2.insert new book 3.remove a book");
                int want=sc.nextInt();
                switch (want){
                    case 1:
                        User.viewTheListOfBooks();
                        break;
                    case 2:
                        System.out.println("please enter name of new book:");
                        String bookName= sc.nextLine();
                        System.out.println("please enter author of new book:");
                        String authorName= sc.nextLine();
                        System.out.println("please enter year of publication new book:");
                        int publication= sc.nextInt();
                        if(!man.addBook(new Book(bookName,authorName,publication))){
                            System.out.println("warning: there is same book! ");
                        }
                        break;
                    case 3:
                        System.out.println("what is name of book do you want the remove it? ");
                        String rmBook= sc.nextLine();
                        System.out.println("are you sure? yes/no ");
                        String sure= sc.nextLine();
                        if(sure.equals("yes")) {
                            if(!man.removeBook(new Book(rmBook,"",0))){
                                System.out.println("warning: book not found! ");
                            }
                        }
                        break;
                }
                System.out.println("what do you do? 0.exit 1.run on");
                runOn= (sc.nextInt() == 1);
            }while (runOn);
        }
    }

    public static void caseRoleLibrarian(String userName,String password){
        Librarian lib=new Librarian(userName,password);
        if(User.login(userName,password) != null) {
            boolean runOn=false;
            do{
                System.out.println("please enter number of want to do? 1.See the book reservation request list 2.Confirm or reject the reservation request");
                int want=sc.nextInt();
                switch (want){
                    case 1:
                        lib.viewTheListOfRequests();
                        break;
                    case 2:
                        System.out.println("which request?(enter number of request)");
                        int reqId= sc.nextInt();
                        Request target= DataBase.requests.get(reqId-1);
                        System.out.println("which one? 1.confirm 2.reject");
                        int reqState= sc.nextInt();
                        if (reqState == 1) {
                            lib.approvingTheRequest(target);
                        } else if (reqState == 2) {
                            lib.rejectingTheRequest(target);
                        }

                        break;
                }
                System.out.println("what do you do? 0.exit 1.run on");
                runOn= (sc.nextInt() == 1);
            }while(runOn);
        }
    }

    public static void caseRoleBookReader(String userName,String password){
        BookReader usr=new BookReader(userName,password);
        if(User.login(userName,password) != null) {
            boolean runOn=false;
            do{
                System.out.println("please enter number of want to do? 1.See books 2.reservation request 3.see state of your request");
                int want=sc.nextInt();
                switch (want){
                    case 1:
                        User.viewTheListOfBooks();
                        break;
                    case 2:
                        System.out.println("which book do you want it (name)?");
                        String bookName= sc.nextLine();
                        Book reqBook = null;
                        for(Book book :DataBase.books){
                            if(book.getName().equals(bookName)){
                                reqBook=book;
                            }
                        }
                        if(reqBook == null){
                            System.out.println("warning: book not found! ");
                            break;
                        }
                        if(reqBook.getStatus().equals(BookStatus.NOT_BOOKABLE)){
                            System.out.println("warning: book is not bookable! ");
                            break;
                        }
                        System.out.println(reqBook);
                        System.out.println("Are you sure? do you want it? yes/no");
                        String sure= sc.nextLine();
                        if(sure.equals("yes")) {
                            Request newReq=new Request(usr,reqBook,RequestStatus.PENDING_APPROVAL);
                            if (!DataBase.requests.contains(newReq)) {
                                DataBase.requests.add(newReq);
                            }else{
                                System.out.println("warning: there is same request!");
                            }
                        }
                        break;
                    case 3:
                        for (Request request : DataBase.requests) {
                            if(request.getUser().getUserName().equals(userName)){
                                System.out.println(request);
                            }
                        }
                        break;
                }
                System.out.println("what do you do? 0.exit 1.run on");
                runOn= (sc.nextInt() == 1);
            }while(runOn);
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        System.out.println("please enter number your role? 1.manager 2.librarian 3.book reader");
        int role= sc.nextInt();
        System.out.println("please enter your name:");
        String userName= sc.nextLine();
        System.out.println("please enter your password:");
        String password= sc.nextLine();
        switch (role) {
            case 1:
                caseRoleManager(userName,password);
                break;
            case 2:
                caseRoleLibrarian(userName,password);
                break;
            case 3:
                caseRoleBookReader(userName,password);
                break;

        }
//        TODO: implement main class based on the newly defined classes
    }

}
