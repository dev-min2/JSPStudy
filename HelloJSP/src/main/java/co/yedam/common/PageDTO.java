package co.yedam.common;

public class PageDTO {
	private int total; // 전체건수
	private int currentPage; // 현재페이지
	private boolean next,prev; // 이전, 이후
	private int startPage, endPage;
	private int lastPage; // 실제 마지막 페이지
	private int boardNo;
	private int showContentCnt;
	
	// 11p 13p 20p
	public PageDTO(int boardNo, int total, int currentPage, int showContentCnt) {
		this.currentPage = currentPage;
		this.showContentCnt = showContentCnt;
		this.lastPage = (int)Math.ceil(((double)total / showContentCnt));
		
		this.boardNo = boardNo;
		this.total = total;
		// EX) 내가 현재 13페이지. 보이는건 10 ~ 13 ~ 15.
		
		// 
		this.endPage = (int)Math.ceil(currentPage / 10.0)  * 10;
		this.startPage = this.endPage - 9;
		
		this.endPage = this.endPage > this.lastPage ? this.lastPage : this.endPage;
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.lastPage;		
	}

	@Override
	public String toString() {
		return "PageDTO [total=" + total + ", currentPage=" + currentPage + ", next=" + next + ", prev=" + prev
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", lastPage=" + lastPage + ", boardNo="
				+ boardNo + ", showContentCnt=" + showContentCnt + "]";
	}
	
	
}
