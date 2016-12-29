package exam.domain;

public class ShoppingCartCell {

	private String bookid;
	private String price;
	private String count;
	private String username;
	private String bookname;

	
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ShoppingCartCell() {
		super();
	}
	public ShoppingCartCell(String bookid, String price, String count, String username, String bookname) {
		super();
		this.bookid = bookid;
		this.price = price;
		this.count = count;
		this.username = username;
		this.bookname = bookname;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	@Override
	public String toString() {
		return "ShoppingCartCell [bookid=" + bookid + ", price=" + price + ", count=" + count + ", username=" + username
				+ ", bookname=" + bookname + "]";
	}
	
}
