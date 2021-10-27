package com.example.demo.service;

import com.example.demo.domain.Member;
import com.sun.org.apache.xpath.internal.operations.String;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    @Override
    public void registerMember(Member member) {
        try {
            // 데이터베이스 연결
            Connection con = ConnectionManager.getConnection();

            // SQL 작성
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO member ");
            sql.append("(email, password, name, contact, birthday, ");
            sql.append(" register_time) ");
            sql.append("VALUES ");
            sql.append("(?, ?, ?, ?, ?, ");
            sql.append(" NOW() ) ");
            System.out.println(sql.toString());

            // PreparedStatment 생성, 수행
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getContact());

            java.lang.String sBirthday = member.getBirthday();
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = dateFormat.parse(sBirthday);
            pstmt.setDate(5, new java.sql.Date(birthday.getTime()));

            pstmt.executeUpdate();

            // 데이터베이스 연결 종료
            con.close();

        } catch (Exception e) {
            throw new RuntimeException("회원 가입 실패", e);
        }

    }

    @Override
    public boolean isEmailDuplicated(java.lang.String email) {

        try {
            // 데이터베이스 연결
            Connection con = ConnectionManager.getConnection();

            // SQL 생성
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT COUNT(*) ");
            sql.append("  FROM member ");
            sql.append(" WHERE email = ? ");

            // Query
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            rs.close();
            pstmt.close();

            // 데이터베이스 연결 해제
            con.close();

            return (count > 0);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Override
    public List<Member> getList() {
        try {
            Connection con = ConnectionManager.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("	email, ");
            sql.append("	name, ");
            sql.append("	contact, ");
            sql.append("	birthday, ");
            sql.append("	register_time ");
            sql.append("  FROM member ");
            sql.append(" ORDER BY register_time ");

            PreparedStatement pstmt = con.prepareStatement(sql.toString());

            List<Member> memberList = new ArrayList<Member>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setEmail(rs.getString("email"));
                member.setName(rs.getString("name"));
                member.setContact(rs.getString("contact"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = rs.getDate("birthday");
                member.setBirthday(dateFormat.format(birthday));

                Date registerTime = rs.getTimestamp("register_time");
                member.setRegisterTime(dateFormat.format(registerTime));

                memberList.add(member);
            }
            rs.close();
            pstmt.close();

            con.close();

            return memberList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static void main(String[] args) {
        try {
            MemberService memberService = new MemberServiceImpl();

            boolean result = memberService.isEmailDuplicated("asdgasdgqw32asgas");
            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
