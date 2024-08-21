import java.util.ArrayList;

public abstract class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void viewTheListOfBooks() {
        for (int i = 0; i < DataBase.books.size(); i++) {
            Book book = DataBase.books.get(i);
            System.out.println(i + ") " + book);
        }
    }

    public static User login(String username, String passWord) {
        for (User user : DataBase.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(passWord)) return user;
        }
        return null;
    }

    public static User signUp(String username, String password, Role role) {
        for (User user : DataBase.users) {
            if (user.getUsername().equals(username)) return null;
        }
        User user = null;
        switch (role) {
            case MANAGER:
                user = new Manager(username, password);
                break;

            case LIBRARIAN:
                user = new Librarian(username, password);
                break;

            case BOOK_READER:
                user = new BookReader(username, password);
                break;

        }
        DataBase.users.add(user);
        return user;
    }

    @Override
    public String toString() {
        return "User { " +
                "username = " + username +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        else {
            User user = (User) o;
            return this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword());
        }
    }

}

class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
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

    public Librarian(String username, String passWord) {
        super(username, passWord);
    }

    public void viewTheListOfRequests() {
        for (int i = 0; i < DataBase.requests.size(); i++) {
            Request request = DataBase.requests.get(i);
            System.out.println(i + ") " + request);
        }
    }

    public void approvingTheRequest(Request request) {
        request.setStatus(RequestStatus.APPROVED);
        User user = request.getUser();
        Book book = request.getBook();
        ((BookReader) user).getBooks().add(book);
        book.setStatus(BookStatus.NOT_BOOKABLE);
        book.setTheLastRecipient(user);
    }

    public void rejectingTheRequest(Request request) {
        request.setStatus(RequestStatus.REJECTED);
    }

}

class BookReader extends User {

    private ArrayList<Book> books = new ArrayList<Book>();

    public BookReader(String username, String password) {
        super(username, password);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public boolean reservationRequest(Book book) {
        if (book.getStatus() == BookStatus.NOT_BOOKABLE) return false;
        Request request = new Request(this, book, RequestStatus.PENDING_APPROVAL);
        DataBase.requests.add(request);
        return true;
    }

    public void viewTheListOfRequests() {
        for (int i = 0; i < DataBase.requests.size(); i++) {
            Request request = DataBase.requests.get(i);
            if (request.getUser().equals(this)) {
                System.out.println(i + ") " + request);
            }
        }
    }

    public boolean removeUnapprovedRequest(Request request) {
        if (request.getStatus() != RequestStatus.PENDING_APPROVAL) return false;
        return DataBase.requests.remove(request);
    }

    public void viewApprovedRequests() {
        for (int i = 0; i < DataBase.requests.size(); i++) {
            Request request = DataBase.requests.get(i);
            if (request.getUser().equals(this) && request.getStatus() == RequestStatus.APPROVED) {
                System.out.println(i + ") " + request);
            }
        }
    }

}
