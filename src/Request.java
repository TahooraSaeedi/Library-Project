public class Request {
    private User user;
    private Book book;
    private RequestStatus status;

    public Request(User User, Book book, RequestStatus status) {
        user = User;
        this.book = book;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request { " +
                "user = " + user +
                " | book = " + book +
                " | status = " + status +
                " }";
    }

}
