package co.yedam.board.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
	private int viewCnt;
	private String image;
	private Date lastUpdate;
}