public class Book {

    private String name;
    private String authorName;
    private Integer yearOfPublication;
    private BookStatus status = BookStatus.BOOKABLE;
    private User theLastRecipient;

    public Book(String name, String authorName, Integer yearOfPublication) {
        this.name = name;
        this.authorName = authorName;
        this.yearOfPublication = yearOfPublication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public User getTheLastRecipient() {
        return theLastRecipient;
    }

    public void setTheLastRecipient(User theLastRecipient) {
        this.theLastRecipient = theLastRecipient;
    }

    @Override
    public String toString() {
        return "Book { " +
                "name = " + name +
                " | authorName = " + authorName +
                " |  yearOfPublication = " + yearOfPublication +
                " | status = " + status +
                " | theLastRecipient = " + theLastRecipient +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        else {
            Book book = (Book) o;
            return this.getName().equals(book.getName()) && this.getAuthorName().equals(book.getAuthorName()) && this.getYearOfPublication() == book.getYearOfPublication();
        }
    }

}
