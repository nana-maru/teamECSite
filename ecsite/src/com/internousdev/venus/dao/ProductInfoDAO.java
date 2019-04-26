package com.internousdev.venus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.venus.dto.ProductInfoDTO;
import com.internousdev.venus.util.DBConnector;
public class ProductInfoDAO {


	//* 商品一覧機能 *//

	public List<ProductInfoDTO> getProductInfoList() throws SQLException{
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT id,product_id,product_name,product_name_kana,category_id,price,image_file_path,image_file_name FROM product_info ORDER BY id";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setProductId(resultSet.getInt("product_id"));
				dto.setProductName(resultSet.getString("product_name"));
				dto.setProductNameKana(resultSet.getString("product_name_kana"));
				dto.setCategoryId(resultSet.getInt("category_id"));
				dto.setPrice(resultSet.getInt("price"));
				dto.setImageFilePath(resultSet.getString("image_file_path"));
				dto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//* 商品詳細機能 *//

	public ProductInfoDTO getProductInfo(int productId){
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		String sql = "SELECT * FROM product_info WHERE product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,productId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTO;
	}


	//* 関連商品表示機能 *//

	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId,int productId,int limitOffset,int limitRowCount){
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();

		String sql = "SELECT id,product_id,product_name,product_name_kana,category_id,price,image_file_path,image_file_name FROM product_info WHERE category_id = ? and product_id not in(?) order by rand() limit ?,?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,categoryId);
			preparedStatement.setInt(2,productId);
			preparedStatement.setInt(3,limitOffset);
			preparedStatement.setInt(4,limitRowCount);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setProductId(resultSet.getInt("product_id"));
				dto.setProductName(resultSet.getString("product_name"));
				dto.setProductNameKana(resultSet.getString("product_name_kana"));
				dto.setCategoryId(resultSet.getInt("category_id"));
				dto.setPrice(resultSet.getInt("price"));
				dto.setImageFilePath(resultSet.getString("image_file_path"));
				dto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTOList.add(dto);

			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}


	//* 商品検索機能(カテゴリ検索[全てのカテゴリ]) *//

		public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList){
			List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
			DBConnector db = new DBConnector();
			Connection connection = db.getConnection();
			String sql = "SELECT id,product_id,product_name,product_name_kana,category_id,price,image_file_path,image_file_name FROM product_info WHERE";
			boolean initializeFlag = true;
			for (String keyword : keywordsList) {
				if (initializeFlag) {
					sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
					initializeFlag = false;
				} else {
					sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				}
			}
			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					ProductInfoDTO dto = new ProductInfoDTO();
					dto.setId(resultSet.getInt("id"));
					dto.setProductId(resultSet.getInt("product_id"));
					dto.setProductName(resultSet.getString("product_name"));
					dto.setProductNameKana(resultSet.getString("product_name_kana"));
					dto.setCategoryId(resultSet.getInt("category_id"));
					dto.setPrice(resultSet.getInt("price"));
					dto.setImageFilePath(resultSet.getString("image_file_path"));
					dto.setImageFileName(resultSet.getString("image_file_name"));
					productInfoDTOList.add(dto);

				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return productInfoDTOList;
		}



	//* 商品検索機能(キーワード及びカテゴリ検索) *//

	public List<ProductInfoDTO> getProductInfoListByKeywords(String[] keywordsList,String categoryId){
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();
		String sql = "SELECT * FROM product_info WHERE";
		boolean initializeFlag = true;

		for(String keyword : keywordsList){
			if(initializeFlag){
			sql += " category_id=" + categoryId + " and ((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			initializeFlag = false;
			}else{
			sql += " or (product_name like '%" +keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		sql += ")";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setProductId(resultSet.getInt("product_id"));
				dto.setProductName(resultSet.getString("product_name"));
				dto.setProductNameKana(resultSet.getString("product_name_kana"));
				dto.setCategoryId(resultSet.getInt("category_id"));
				dto.setPrice(resultSet.getInt("price"));
				dto.setImageFilePath(resultSet.getString("image_file_path"));
				dto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTOList.add(dto);

			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

}
