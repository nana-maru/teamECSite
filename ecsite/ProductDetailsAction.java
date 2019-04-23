package com.internousdev.venus.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.ProductInfoDAO;
import com.internousdev.venus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{
	private int id;
	private int productId;
	private String productName;
	private String productNameKana;
	private int price;
	private String releaseCompany;
	private Date releaseDate;
	private String imageFilePath;
	private String imageFileName;
	private Date registDate;
	private Date updateDate;
	private String productDescription;
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductList;
	private Map<String,Object> session;

	public String execute(){

		if(session.isEmpty()) {
			return "sessionTimeout";
		}

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		productInfoDTO = productInfoDAO.getProductInfo(productId);

		productId = productInfoDTO.getProductId();
		productName = productInfoDTO.getProductName();
		productNameKana = productInfoDTO.getProductNameKana();
		price = productInfoDTO.getPrice();
		releaseCompany = productInfoDTO.getReleaseCompany();
		releaseDate =  productInfoDTO.getReleaseDate();
		imageFilePath = productInfoDTO.getImageFilePath();
		imageFileName = productInfoDTO.getImageFileName();
		registDate = productInfoDTO.getRegistDate();
		updateDate = productInfoDTO.getUpdateDate();
		productDescription = productInfoDTO.getProductDescription();

		//* 購入個数リストを作成 *//
		productCountList = new ArrayList<Integer>();
		for(int i=1;i<=5;i++){
			productCountList.add(i);
		}

		//* 関連商品を探す *//
		relatedProductList = productInfoDAO.getProductInfoListByCategoryId(productInfoDTO.getCategoryId(), productInfoDTO.getProductId(), 0, 3);

		return SUCCESS;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}



	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<Integer> getProductCountList() {
		return productCountList;
	}

	public void setProductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductList() {
		return relatedProductList;
	}

	public void setRelatedProductList(List<ProductInfoDTO> relatedProductList) {
		this.relatedProductList = relatedProductList;
	}


}
