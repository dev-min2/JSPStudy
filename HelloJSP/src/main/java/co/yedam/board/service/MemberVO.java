package co.yedam.board.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	private String mId;
	private String pass;
	private String name;
	private String phone;
	private String responsibility;
}

