package exam.domain;


public class SeeShoppingCartItem {
	private String bookname;
	private String goodprice;
	private String goodcount;
	private String goodAllPrice;
	private String bookid;
	private String select;
	private byte[] cover;
	
	
	public String getGoodprice() {
		return goodprice;
	}
	public void setGoodprice(String goodprice) {
		this.goodprice = goodprice;
	}
	public String getGoodcount() {
		return goodcount;
	}
	public void setGoodcount(String goodcount) {
		this.goodcount = goodcount;
	}
	public String getGoodAllPrice() {
		return goodAllPrice;
	}
	public void setGoodAllPrice(String goodAllPrice) {
		this.goodAllPrice = goodAllPrice;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public byte[] getCover() {
		return cover;
	}
	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
}
