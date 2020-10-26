package ceadarProject;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import boPackage.BOClass;
import commonUtils.CommonUtils;

@WebServlet("/APIMethod")
public class APIMethod extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		BOClass boClass = null;
		AllFunctions allFunctions = null;
		PrintWriter out = null;
		String returnResponse = null;
		try {
			allFunctions = new AllFunctions();
			boClass = new BOClass();
			if(CommonUtils.isNotNullOrEmpty(req.getParameter("function_name"))) {
				boClass.setFunctionName(req.getParameter("function_name"));
				switch(boClass.getFunctionName()) {
				case "getTableData":
					boClass = allFunctions.getTableData(boClass);
					break;
				case "saveTableData":
					boClass.setSaveData((req.getParameter("data")).toString());
					boClass.setStrHeader((req.getParameter("header")).toString());
					boClass = allFunctions.saveTableData(boClass);
					break;
				}
			}
			returnResponse = new Gson().toJson(boClass);
			resp.setContentType("application/json");
			out = resp.getWriter();
			out.print(returnResponse);
			out.flush();
		}catch(Exception e) {
			
		}
	}

}
