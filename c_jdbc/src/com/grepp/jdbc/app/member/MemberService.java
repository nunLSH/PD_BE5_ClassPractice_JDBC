package com.grepp.jdbc.app.member;


import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.infra.exception.ValidException;
import java.sql.Connection;
import java.util.Optional;
import javax.swing.ViewportLayout;

// NOTE 02 Service
// 비지니스 로직을 구현
// DB 의 transaction 관리 (commit/ rollback)
public class MemberService {

    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private MemberDao memberDao = new MemberDao();

    public Optional<MemberDto> signup(MemberDto dto) {

        Connection conn = jdbcTemplate.getConnection();

        try {
            Optional<MemberDto> res = memberDao.insert(conn, dto);
            jdbcTemplate.commit(conn);
            return res;
        } catch (DataAccessException e){
            jdbcTemplate.rollback(conn);
            throw  e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
}
