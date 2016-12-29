package exam.domain;

import java.io.InputStream;
import java.util.Arrays;
public class Book {
	private String id;
	private String bookname;
	private String author;
	private String press;
	private String presstime;
	private String price;
	private String isbn;
	private String c3code;
	private String bookcounts;
	private String booksell;
	private String introduction;
	private String recommend;
	private byte[] cover;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public String getEasyBookName(){
		String easybookname=bookname;
		if(easybookname.length()>10){
			easybookname=bookname.substring(0, 9)+"..";
		}
		return easybookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPresstime() {
		return presstime;
	}
	public void setPresstime(String presstime) {
		this.presstime = presstime;
	}
	public String getPrice() {
		return "гд"+price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getC3code() {
		return c3code;
	}
	public void setC3code(String c3code) {
		this.c3code = c3code;
	}
	public String getBookcounts() {
		return bookcounts;
	}
	public void setBookcounts(String bookcounts) {
		this.bookcounts = bookcounts;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public Book() {
		super();
	}
	public String getBooksell() {
		return booksell;
	}
	public void setBooksell(String booksell) {
		this.booksell = booksell;
	}
	public Book(String id, String bookname, String author, String press, String presstime, String price, String isbn,
			String c3code, String bookcounts, String booksell, String introduction, String recommend, byte[] cover) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.press = press;
		this.presstime = presstime;
		this.price = price;
		this.isbn = isbn;
		this.c3code = c3code;
		this.bookcounts = bookcounts;
		this.booksell = booksell;
		this.introduction = introduction;
		this.recommend = recommend;
		this.cover = cover;
	}
	public byte[] getCover() {
		return cover;
	}
	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookname=" + bookname + ", author=" + author + ", press=" + press + ", presstime="
				+ presstime + ", price=" + price + ", isbn=" + isbn + ", c3code=" + c3code + ", bookcounts="
				+ bookcounts + ", booksell=" + booksell + ", introduction=" + introduction + ", recommend=" + recommend
				+ ", cover=" + Arrays.toString(cover) + "]";
	}
}
