package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UpdateService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 1. form에서 넘어오는 데이터(파라미터)들 가져오기
		 2. 데이터를 UserVO로 포장하기
		 3. dao의 updateUser()를 호출해서 회원정보 수정 진행하기
		 4. 수정된 정보로 세션 데이터를 교환(덮어 씌우기)
		 5. alert()을 이용해서 사용자에게 응답 주고 마이페이지로 이동.
		 */
		
		
		UserVO user = new UserVO();
		user.setUserId(request.getParameter("id"));
		user.setUserName(request.getParameter("name"));
		user.setUserEmail(request.getParameter("email"));
		user.setUserAddress(request.getParameter("address"));
		
		UserDAO dao = UserDAO.getInstance();
		dao.updateUser(user);
		
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("user", dao.getUserInfo(user.getUserId()));
		
		String htmlCode;
		try {
			PrintWriter out = response.getWriter();
			htmlCode = "<script>\r\n"
                    + "alert('회원정보가 수정되었습니다.');\r\n"
                    + "location.href='/MyWeb/myPage.user';\r\n"
                    + "</script>";
			out.print(htmlCode);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
