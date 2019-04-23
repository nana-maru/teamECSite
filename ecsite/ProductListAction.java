package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.ProductInfoDAO;
import com.internousdev.venus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{
	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	private Map<String,Object> session;

	public String execute() throws SQLException {

		if(session.isEmpty()) {
			return "sessionTimeout";
		}

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTOList = productInfoDAO.getProductInfoList();

		return SUCCESS;
}
	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public List<ProductInfoDTO> getProductInfoDTOList(){
		return this.productInfoDTOList;
	}
	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}
}
