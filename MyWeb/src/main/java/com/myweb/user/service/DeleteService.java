package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class DeleteService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String checkPw = request.getParameter("check_pw");
		
		HttpSession session = request.getSession();
		String id = ((UserVO) session.getAttribute("user")).getUserId();

		UserDAO dao = UserDAO.getInstance();
		response.setContentType("text/html; charset=UTF-8");
		
		String htmlCode;
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			if(dao.userCheck(id, checkPw) == 1) {
				dao.deleteUser(id);
				session.invalidate();
				htmlCode = "<script>\r\n"
                        + "alert('회원 탈퇴가 정상적으로 처리되었습니다.');\r\n"
                        + "location.href='/MyWeb';\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
			} else {
				htmlCode = "<script>\r\n"
	                    + "alert('비밀번호가 틀렸습니다.');\r\n"
	                    + "location.href='/MyWeb/myPage.user';\r\n"
	                    + "</script>";
				out.print(htmlCode);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		
	}

}
