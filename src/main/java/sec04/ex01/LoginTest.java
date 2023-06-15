package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/login")
public class LoginTest extends HttpServlet {
	
	
	// 이 서버에 들어온 사용자들을 컨텍스트(pro10) 범위에 저장해 두어야 한다.
	
	 List userList=new ArrayList();;
	 ServletContext context=null;
	 String user_id=null;
	 HttpSession sess=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding( "utf-8" );
		response.setContentType("text/html;charset=utf-8");
		context= getServletContext();
		
		PrintWriter out = response.getWriter();

		
		
//		System.out.println(context.getServletContextName());
		
		
		
		user_id = request.getParameter("user_id");
		System.out.println(user_id);
		String user_pw = request.getParameter("user_pw");
		
		LoginImpl loginUser=new LoginImpl(user_id,user_pw );
		
		 sess= request.getSession();
		
		
		
		
		
		if(sess.isNew()) {
			sess.setAttribute("loginUser", loginUser);
			userList.add(user_id);
			context.setAttribute("userList", userList);
		}
		
		
		out.println("<html><body>");
		out.println("이름는 " + user_id + "<br>");
		
		out.println("총 접속자수는"+ LoginImpl.total_user + "명");
		out.println("접속 아이디:<br>");
		
		List list = (ArrayList) context.getAttribute("userList");
		
		for(int i=0 ; i<list.size(); i++) {
			out.println(list.get(i)+"<br>");
		}
		out.println("<a href='logout?user_id=" + user_id + "'>로그아웃 </a>");
		out.println("</body></html>");
	}

}
