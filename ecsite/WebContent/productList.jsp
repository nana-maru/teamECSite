<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/venus.css">
<link href="https://fonts.googleapis.com/css?family=Great+Vibes&amp;subset=latin-ext" rel="stylesheet">
<title>商品一覧画面</title>
</head>

<body>
<jsp:include page="header.jsp"/>

<div id="contents">
	<div class="top">
		<h1>商品一覧画面</h1>
	</div>

	<s:if test="keywordsErrorMessageList != null && keywordsErrorMessageList.size()>0">
		<div class="error">
			<s:iterator value="keywordsErrorMessageList"><s:property /><br></s:iterator>
		</div>
	</s:if>

	<s:elseif test="productInfoDTOList !=null && productInfoDTOList.size()>0">
		<div class="productList">
			<s:iterator value="productInfoDTOList">
				<div class="productListBox">
					<ul>
						<li>
							<a href='<s:url action="ProductDetailsAction">
							<s:param name="productId" value="%{productId}"/>
							</s:url>' class="product"><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' /><br>
							<s:property value="productName"/><br>
							<s:property value="productNameKana"/><br>
							<s:property value="price"/>円<br></a>
						</li>
					</ul>
				</div>
			</s:iterator>
		</div>
	</s:elseif>

	<s:else>
		<div class="info">
			検索結果がありません。
		</div>
	</s:else>
</div>

</body>
</html>