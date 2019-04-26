<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/venus.css">
<link rel="stylesheet" href="./css/venusProductDetails.css">
<link href="https://fonts.googleapis.com/css?family=Great+Vibes&amp;subset=latin-ext" rel="stylesheet">
<title>商品詳細画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="top">
		<h1>商品詳細画面</h1>
	</div>
	<s:if test="!productName != null && !productName.isEmpty()">
		<s:form action="AddCartAction">
			<div class="box1">
					<div class="left">
						<img
							src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'><br>
					</div>
					<div class="right">
						<table class="vertical-list-table">
							<tr>
								<th scope="row"><s:label value="商品名" /></th>
								<td><s:property value="productName" /></td>
							</tr>

							<tr>
								<th scope="row"><s:label value="商品名ふりがな" /></th>
								<td><s:property value="productNameKana" /></td>
							</tr>

							<tr>
								<th scope="row"><s:label value="値段" /></th>
								<td><s:property value="price" />円<br>
							</tr>

							<tr>
								<th scope="row"><s:label value="購入個数" /></th>
								<td><s:select name="productCount"
										list="%{productCountList}" />個</td>
							</tr>

							<tr>
								<th scope="row"><s:label value="発売会社名" /></th>
								<td><s:property value="releaseCompany" /></td>
							</tr>

							<tr>
								<th scope="row"><s:label value="発売年月日" /></th>
								<td><s:property value="releaseDate" /></td>
							</tr>

							<tr>
								<th scope="row"><s:label value="商品詳細情報" /></th>
								<td><s:property value="productDescription" /></td>
							</tr>
						</table>

					<div class="submit_btn_box">
						<s:submit value="カートに追加" class="submit_btn" />
					</div>

					</div>

				<s:hidden name="productId" value="%{productId}" />
			</div>
		</s:form>

		<s:if test="relatedProductList.size()>1">
			<div class="productList">
				<h2>【関連商品】</h2>

				<s:iterator value="relatedProductList">
					<div class="productListBox">
						<ul>
							<li>
								<a href='<s:url action="ProductDetailsAction">
								<s:param name="productId" value="%{productId}"/>
								</s:url>' class="product"><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="productListImg"/><br>
								<s:property value="productName"/><br></a>
							</li>
						</ul>
					</div>
				</s:iterator>
			</div>
		</s:if>

	</s:if>
	<s:else>
		<div class="info">商品の詳細情報がありません。</div>
	</s:else>
</body>
</html>