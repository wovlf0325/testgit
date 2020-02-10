package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyBiz;
import com.mvc.biz.MyBizImpl;


/**
 * Servlet implementation class MyServlet
 */
@WebServlet(urlPatterns = {"/mylist", "/mydetail", "/writeform", "mywriteres"})
// urlPatterns 배열을 만들어 배열 안에  수행할 일에 따라 url을 다르게 설정해 줄 수 있다.
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyBiz biz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void getRequest(HttpServletRequest request, HttpServletResponse response) {
    	biz = new MyBizImpl();
    	
    	String command = request.getRequestURI();
    	System.out.printf("[%s]\n",command);
    	
    	if(command.endsWith("/mylist")) {
    		doMyList(request, response);
    	} else if(command.endsWith("/mydetail")) {
    		doMyDetail(request,response);
    	} else if(command.endsWith("/writeform")) {
    		doMyWriteForm(request,response);
    	} else if(command.endsWith("/mywriteres")) {
    		doMyWriteRes(request, response);
    	}
    }
    
    private void doMyWriteRes(HttpServletRequest request, HttpServletResponse response) {
    	String writer = request.getParameter("writer");
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	MyDto dto = new Mydto(0, writer, title, content, null);
    	int res = biz.insert(dto);
    	if(res > 0) {
    		jsResponse("글 작성 성공", "mylist", response);
    	} else {
    		jsResponse("글 작성 실패", "writeform", response);
    	}
	}

	private void doMyWriteForm(HttpServletRequest request, HttpServletResponse response) {
    	response.sendRedirect("mywrite.jsp");
    }
    
    private void doMyDetail(HttpServletRequest request, HttpServletResponse response) {
    	int seq = Integer.parseInt(request.getParameter("seq"));
    	MyDto dto = biz.selectOne(seq);
    	request.setAttribute("dto",  dto);
    	dispatch("mydetail.jsp",request, response);
    }
    
    private void doMyList(HttpServletRequest request, HttpServletResponse response) {
    	List<MyDto> list = biz.selectList();
    	request.setAttribute("list", list);
    	dispatch("mylist.jsp",request, response);
    }
    
    private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher dispatch = request.getRequestDispatcher(url);
    	dispatch.forward(request, response);
    }
    
    private void jsResponse(String msg, String url, HttpServletResponse response) {
    	PrintWriter out = response.getWriter();
    	out.println("");
    	out.println("");
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

}
