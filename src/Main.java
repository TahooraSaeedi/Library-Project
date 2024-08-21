import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void caseRoleManager(User user) {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        Manager manager = (Manager) user;
        System.out.println("Please choose an action:\n1.View the list of books\n2.Add new book\n3.Remove the existing book\n4.Back to start menu");
        int action = scanner.nextInt();
        scanner.nextLine();
        switch (action) {
            case 1:
                manager.viewTheListOfBooks();
                caseRoleManager(user);
                break;
            case 2:
                System.out.println("Please enter the name of the new book:");
                String name = scanner.nextLine();
                System.out.println("Please enter the name of the author of  the new book:");
                String authorName = scanner.nextLine();
                System.out.println("Please enter the year of publication ot the new book:");
                int yearOfPublication = scanner.nextInt();
                Book newBook = new Book(name, authorName, yearOfPublication);
                boolean result = manager.addBook(newBook);
                if (result) {
                    System.out.println("The book has been successfully added to the library.");
                } else {
                    System.out.println("This book is already available in the library.");
                }
                caseRoleManager(user);
                break;
            case 3:
                manager.viewTheListOfBooks();
                System.out.println("Please enter the number of the book you want to delete:");
                int index = scanner.nextInt();
                Book book = DataBase.books.get(index);
                manager.removeBook(book);
                System.out.println("The book has been successfully removed from the library.");
                caseRoleManager(user);
                break;
            case 4:
                startMenu();
                break;
        }
    }

    public static void caseRoleLibrarian(User user) {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        Librarian librarian = (Librarian) user;
        System.out.println("Please choose an action:\n1.View the list of requests\n2.Approving the request\n3.Rejecting the request\n4.Back to start menu");
        int action = scanner.nextInt();
        scanner.nextLine();
        int index;
        Request request;
        switch (action) {
            case 1:
                librarian.viewTheListOfRequests();
                caseRoleLibrarian(user);
                break;
            case 2:
                librarian.viewTheListOfRequests();
                System.out.println("Please enter the number of the request you want to approve:");
                index = scanner.nextInt();
                request = DataBase.requests.get(index);
                librarian.approvingTheRequest(request);
                System.out.println("Request has been successfully approved.");
                caseRoleLibrarian(user);
                break;
            case 3:
                librarian.viewTheListOfRequests();
                System.out.println("Please enter the number of the request you want to reject:");
                index = scanner.nextInt();
                request = DataBase.requests.get(index);
                librarian.rejectingTheRequest(request);
                System.out.println("Request has been successfully rejected.");
                caseRoleLibrarian(user);
                break;
            case 4:
                startMenu();
                break;
        }
    }

    public static void caseRoleBookReader(User user) {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        BookReader bookReader = (BookReader) user;
        System.out.println("Please choose an action:\n1.View the list of books\n2.Reservation request\n3.View the list of all requests\n4.Remove unapproved request\n5.View approved Requests\n6.Back to start menu");
        int action = scanner.nextInt();
        scanner.nextLine();
        int index;
        boolean result;
        switch (action) {
            case 1:
                bookReader.viewTheListOfBooks();
                caseRoleBookReader(user);
                break;
            case 2:
                bookReader.viewTheListOfBooks();
                System.out.println("Please enter the number of the book you want to reserve:");
                index = scanner.nextInt();
                Book book = DataBase.books.get(index);
                result = bookReader.reservationRequest(book);
                if (result) {
                    System.out.println("Book reservation request has been successfully submitted.");
                } else {
                    System.out.println("This book is not available for reservation.");
                }
                caseRoleBookReader(user);
                break;
            case 3:
                bookReader.viewTheListOfRequests();
                caseRoleBookReader(user);
                break;
            case 4:
                bookReader.viewTheListOfRequests();
                System.out.println("Please enter the number of the request you want to delete:");
                index = scanner.nextInt();
                Request request = DataBase.requests.get(index);
                result = bookReader.removeUnapprovedRequest(request);
                if (result) {
                    System.out.println("The selected request has been successfully deleted.");
                } else {
                    System.out.println("The selected request cannot be deleted.");
                }
                caseRoleBookReader(user);
                break;
            case 5:
                bookReader.viewApprovedRequests();
                caseRoleBookReader(user);
                break;
            case 6:
                startMenu();
        }
    }

    public static void startMenu() {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        System.out.println("1.Login\n2.Signup");
        int select = scanner.nextInt();
        scanner.nextLine();
        switch (select) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
        }
    }

    public static void login() {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        User user = User.login(username, password);
        if (user != null) {
            System.out.println("You have successfully logged in.");
            if (user instanceof Manager) {
                caseRoleManager(user);
            } else if (user instanceof Librarian) {
                caseRoleLibrarian(user);
            } else if (user instanceof BookReader) {
                caseRoleBookReader(user);
            }
        } else {
            System.out.println("Username or password is incorrect.");
            startMenu();
        }
    }

    public static void signup() {
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        System.out.println("Choose your role:\n1.Manager\n2.Librarian\n3.Book reader");
        int role = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        User user;
        switch (role) {
            case 1:
                user = User.signUp(username, password, Role.MANAGER);
                if (user != null) {
                    caseRoleManager(user);
                } else {
                    System.out.println("Username is not available.\nPlease try again with another username.");
                    startMenu();
                }
                break;
            case 2:
                user = User.signUp(username, password, Role.LIBRARIAN);
                if (user != null) {
                    caseRoleLibrarian(user);
                } else {
                    System.out.println("Username is not available.\nPlease try again with another username.");
                    startMenu();
                }
                break;
            case 3:
                user = User.signUp(username, password, Role.BOOK_READER);
                if (user != null) {
                    caseRoleBookReader(user);
                } else {
                    System.out.println("Username is not available.\nPlease try again with another username.");
                    startMenu();
                }
                break;
        }
    }

    public static void main(String[] args) {
        startMenu();
    }

}
