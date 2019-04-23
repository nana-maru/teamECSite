package com.internousdev.venus.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.DestinationInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		String result = ERROR;
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();

		int count = destinationInfoDAO.insert(
				(session.get("userId").toString()),
				(session.get("familyName").toString()),
				(session.get("firstName").toString()),
				(session.get("familyNameKana").toString()),
				(session.get("firstNameKana").toString()),
				(session.get("userAddress").toString()),
				(session.get("telNumber").toString()),
				(session.get("email").toString())
				);

		if(count > 0){
			result = SUCCESS;
		}
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
