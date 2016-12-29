package exam.domain;

public class CartGoodBase {
	private String bookname;
	private String price;
	private String allprice;
	private String count;
	private String select;
	private String bookid;
	
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAllprice() {
		return allprice;
	}
	public void setAllprice(String allprice) {
		this.allprice = allprice;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public CartGoodBase(String bookname, String price, String allprice, String count, String select, String bookid) {
		super();
		this.bookname = bookname;
		this.price = price;
		this.allprice = allprice;
		this.count = count;
		this.select = select;
		this.bookid = bookid;
	}
	public CartGoodBase() {
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	
}
