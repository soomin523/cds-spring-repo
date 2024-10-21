package com.human.cds.service;

import java.util.List;

import com.human.cds.vo.ProductsVO;
import com.human.cds.vo.ProductsVO.Products;

public interface ProductsService {
    int insertProducts(ProductsVO data);

	List<ProductsVO.Products> getProductsList();

	int updateAreaName(String areaName, String sigunguName, String contentid);

	List<ProductsVO.Products> getProductsPage(int page, int size);


}