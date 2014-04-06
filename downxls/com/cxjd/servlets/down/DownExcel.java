package com.cxjd.servlets.down;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cxjd.common.Dictionary;
import com.cxjd.common.ExcelHelper;
import com.cxjd.domain.item.Apply;
import com.cxjd.service.item.ApplyService;
import com.cxjd.service.item.ClaimerService;


/**
 * Excel下载页
 * 
 *@author 李腾超
 *@since 2014-01-17
 *
 */
public class DownExcel extends HttpServlet {

	private static final long serialVersionUID = 4907504451447919727L;

	public DownExcel() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String itemName = request.getParameter("in");
		String applyStatus = request.getParameter("as");
		response.setCharacterEncoding("UTF-8");
		if(itemName == null || applyStatus == null){
			response.setContentType("text/plain");
			response.getWriter().write("非法进入！");
			return;
		}
			
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename*=UTF-8''2010-10-10.xls");
		
		
		ApplyService applyService = new ApplyService();
		ClaimerService claimerService = new ClaimerService();
		List<Apply> results = applyService.readToExcel(Integer.parseInt(applyStatus), itemName);
		
		
		for (Apply apply : results) {
			apply.setOtherCliamers(claimerService.readClaimerByApplyKey(apply.getId(), Dictionary.claimerType_main));
			apply.setOtherCliamers(claimerService.readClaimerByApplyKey(apply.getId(), Dictionary.claimerType_other));
		}
		
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			ExcelHelper eh = new ExcelHelper();
			eh.initXlsStream(out);
			eh.operatorXls(results);
			eh.closeXlsStream();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public void init() throws ServletException {
		// Put your code here
	}

}
