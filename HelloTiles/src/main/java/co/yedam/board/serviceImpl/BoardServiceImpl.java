package co.yedam.board.serviceImpl;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.admin.service.MemberVO;
import co.yedam.board.mapper.BoardMapper;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.common.DataSourceMybatis;

public class BoardServiceImpl implements BoardService {
	private SqlSessionFactory s = DataSourceMybatis.getInstance();
	private SqlSession sqlSession = s.openSession(true);
	private BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	
	public void close() {
		sqlSession.close();
	}
	
	@Override
	public List<BoardVO> getBoardList() {
		return mapper.selectBoardAll();
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		mapper.updateViewCnt(boardNo);
		return mapper.select(boardNo);
	}

	@Override
	public boolean addBoard(BoardVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean editBoard(BoardVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return mapper.delete(boardNo) > 0 ? true : false;
	}

	@Override
	public boolean updateViewCnt(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.updateViewCnt(boardNo) > 0 ? true : false;
	}

	@Override
	public MemberVO loginCheck(String id, String password) {
		if(id == null || id.isEmpty() || password == null || password.isEmpty()) {
			System.out.println("비어있음.");
		}
		
		return mapper.selectMember(id, password);
	}

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return mapper.selectMemberAll();
	}
}
