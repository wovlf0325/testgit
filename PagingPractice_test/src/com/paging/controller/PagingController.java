package com.paging.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.biz.PagingBiz;
import com.paging.biz.PagingBizImpl;
import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

/**
 * Servlet implementation class PagingController
 */
@WebServlet("/paging.do")
public class PagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PagingBiz biz = new PagingBizImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		if(command.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			PagingDto dto = new PagingDto();
			dto.setPage(page);
			dto.setRows(10);
			dto.setPagescale(3);
			dto = biz.paging(dto);
			List<BoardDto> list = biz.selectList(dto);
			request.setAttribute("list", list);
			request.setAttribute("paging", dto);
			dispatch("list.jsp", request, response);
			
		} else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			BoardDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
			
		}
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"');");
		out.println("</script>");
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void historyBack(String msg, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("history.back()");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}
