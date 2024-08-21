import java.util.ArrayList;
import java.util.Objects;

public abstract class User {
    private String userName;
    private String password;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.password = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void changePassWord(String passWord) {
        this.password = passWord;
    }

    public void viewTheListOfBooks() {
        for (Book book : DataBase.books) {
            System.out.println(book);
        }
    }

    public static User login(String userName, String passWord) {
        for (User user : DataBase.users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(passWord)) return user;
        }
        return null;
    }

    public static User signUp(String userName, String passWord, Role role) {
        for (User user : DataBase.users) {
            if (user.getUserName().equals(userName)) return null;
        }
        User user = null;
        switch (role) {
            case MANAGER:
                user = new Manager(userName, passWord);
                break;

            case LIBRARIAN:
                user = new Librarian(userName, passWord);
                break;

            case BOOK_READER:
                user = new BookReader(userName, passWord);
                break;
        }
        DataBase.users.add(user);
        return user;
    }

    @Override
    public String toString() {
        return "User { " +
                "userName = " + userName +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        else {
            User user = (User) o;
            return this.getUserName().equals(user.getUserName()) && this.getPassword().equals(user.getPassword());
        }
    }

}

class Manager extends User {

    public Manager(String userName, String password) {
        super(userName, password);
    }


    public boolean addBook(Book book) {
        if (!DataBase.books.contains(book)) {
            DataBase.books.add(book);
            return true;
        }
        return false;
    }

    public boolean removeBook(Book book) {
        return DataBase.books.remove(book);
    }

}

class Librarian extends User {

    public Librarian(String userName, String passWord) {
        super(userName, passWord);
    }

    public void viewTheListOfRequests() {
        for (Request request : DataBase.requests) {
            System.out.println(request);
        }
    }

    public void approvingTheRequest(Request request) {
        request.setStatus(RequestStatus.APPROVED);
        User user = request.getUser();
        Book book = request.getBook();
        ((BookReader) user).addBook(book);
        book.setStatus(BookStatus.NOT_BOOKABLE);
        book.setTheLastRecipient(user);
    }

    public void rejectingTheRequest(Request request) {
        request.setStatus(RequestStatus.REJECTED);
    }

}

class BookReader extends User {

    private ArrayList<Book> books = new ArrayList<Book>();

    public BookReader(String userName, String passWord) {
        super(userName, passWord);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.getBooks().add(book);
    }

    public void reservationRequest(Book book) {
        Request request = new Request(this, book, RequestStatus.PENDING_APPROVAL);
    }

    public void viewTheListOfRequests() {
        for (Request request : DataBase.requests) {
            if (request.getUser().equals(this)) {
                System.out.println(request);
            }
        }
    }

    public boolean removeUnapprovedRequest(Request request) {
        boolean result;
        if (request.getStatus() != RequestStatus.PENDING_APPROVAL) return false;
        return DataBase.requests.remove(request);
    }

    public void viewApprovedRequests() {
        for (Request request : DataBase.requests) {
            if (request.getUser().equals(this) && request.getStatus() == RequestStatus.APPROVED) {
                System.out.println(request);
            }
        }
    }

}
