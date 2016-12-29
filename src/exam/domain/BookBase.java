package exam.domain;

public class BookBase {
	private String bookname;
	private String id;
	private String price;
	private String booksell;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBooksell() {
		return booksell;
	}
	public void setBooksell(String booksell) {
		this.booksell = booksell;
	}
	public BookBase(String bookname, String id, String price, String booksell) {
		super();
		this.bookname = bookname;
		this.id = id;
		this.price = price;
		this.booksell = booksell;
	}
}
