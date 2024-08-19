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
        return user;
    }

    @Override
    public String toString() {
        return "User { " +
                "userName = " + userName +
                " }";
    }

}

class Manager extends User {

    public Manager(String userName, String password) {
        super(userName, password);
    }

    public void viewTheListOfBooks() {
        for (Book book : DataBase.books) {
            System.out.println(book);
        }
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
        // TODO: add request.getBook() to list of request.getUser() books
        book.setStatus(BookStatus.NOT_BOOKABLE);
        book.setTheLastRecipient(user);
    }

    public void rejectingTheRequest(Request request) {
        request.setStatus(RequestStatus.REJECTED);
    }

}

class BookReader extends User {

    public BookReader(String userName, String passWord) {
        super(userName, passWord);
    }

    // TODO: implement the user class

}
