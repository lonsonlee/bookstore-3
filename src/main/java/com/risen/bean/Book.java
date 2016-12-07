package com.risen.bean;

public class Book {
	private int bid;//书id
	
	private String bname;//书名
	
	private String author;//作者
	
	private double pricing;//定价
	
	private double currPrice;//当前价
	
	private double discount;//折扣
	
	private String press;//出版社
	
	private String publicationTime;//出版时间
	
	private int edition;//版次
	
	private int pageNum;//页数
	
	private int wordNum;//字数
	
	private String printTime;//印刷时间
	
	private int bookSize;//书的开本
	
	private String paperCharacter;//纸质
	
	private int cid;//所属的二级分类
	
	private String image_w;//大图
	
	private String image_b;//小图

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPricing() {
		return pricing;
	}

	public void setPricing(double pricing) {
		this.pricing = pricing;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPublicationTime() {
		return publicationTime;
	}

	public void setPublicationTime(String publicationTime) {
		this.publicationTime = publicationTime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getWordNum() {
		return wordNum;
	}

	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public int getBookSize() {
		return bookSize;
	}

	public void setBookSize(int bookSize) {
		this.bookSize = bookSize;
	}

	public String getPaperCharacter() {
		return paperCharacter;
	}

	public void setPaperCharacter(String paperCharacter) {
		this.paperCharacter = paperCharacter;
	}

	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getImage_w() {
		return image_w;
	}

	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}

	public String getImage_b() {
		return image_b;
	}

	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", author=" + author + ", pricing=" + pricing + ", currPrice="
				+ currPrice + ", discount=" + discount + ", press=" + press + ", publicationTime=" + publicationTime
				+ ", edition=" + edition + ", pageNum=" + pageNum + ", wordNum=" + wordNum + ", printTime=" + printTime
				+ ", bookSize=" + bookSize + ", paperCharacter=" + paperCharacter + ", cid=" + cid + ", image_w="
				+ image_w + ", image_b=" + image_b + "]";
	}


	
	
}
