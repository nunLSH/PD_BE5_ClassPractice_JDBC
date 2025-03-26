package com.grepp.jdbc.app.member.validator;

import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.dto.form.LeaveForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;
import java.util.Optional;
import javax.swing.ViewportLayout;

public class LeaveFormValidator implements Validator<LeaveForm> {

    private MemberDao memberDao = new MemberDao();
    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

    @Override
    public void validate(LeaveForm form) {
        Connection conn = jdbcTemplate.getConnection();
        Optional<MemberDto> member = memberDao.selectById(conn, form.getUserId());

        if (member.isEmpty()) throw  new ValidException("존재하지 않는 사용자압니다.");
        if (member.get().getLeave()) throw new ValidException("이미 탈퇴한 회원입니다.");
    }

}
