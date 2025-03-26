package com.grepp.jdbc.app.member;

import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dao.MemberInfoDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.dto.MemberInfoDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.infra.exception.ValidException;
import java.sql.Connection;
import java.util.Optional;

// Note 02 Service
// 비지니스로직을 구현
// DB의 transaction 관리 (commit/rollback)
public class MemberService {

    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private final MemberDao memberDao = new MemberDao();
    private final MemberInfoDao memberInfoDao = new MemberInfoDao();

    public MemberDto signup(MemberDto dto) {

        Connection conn = jdbcTemplate.getConnection();

        try{
            memberDao.insert(conn, dto);
            MemberInfoDto info = new MemberInfoDto();

            info.setUserId(dto.getUserId());
            memberInfoDao.insert(conn, info);

            jdbcTemplate.commit(conn);
            return dto;
        }catch (DataAccessException e){
            jdbcTemplate.rollback(conn);
            throw e;
        }finally {
            jdbcTemplate.close(conn);
        }
    }
}