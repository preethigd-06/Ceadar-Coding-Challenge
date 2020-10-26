package boPackage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class BOClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String functionName;
	private boolean status;
	private String errMsg;
	private List<HashMap<String, String>> dataList;
	private List<String> header;
	private String saveData;
	private String strHeader;
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public List<HashMap<String, String>> getDataList() {
		return dataList;
	}
	public void setDataList(List<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public List<String> getHeader() {
		return header;
	}
	public void setHeader(List<String> header) {
		this.header = header;
	}
	public String getSaveData() {
		return saveData;
	}
	public void setSaveData(String saveData) {
		this.saveData = saveData;
	}
	public String getStrHeader() {
		return strHeader;
	}
	public void setStrHeader(String strHeader) {
		this.strHeader = strHeader;
	}

}
