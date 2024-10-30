package com.human.cds.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.cds.repository.ProductsDAO;

import com.human.cds.vo.ProductsVO;
import com.human.cds.vo.ProductsVO.Products;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDAO dao;


	@Override
	public int insertProducts(ProductsVO data) {
		return dao.insertProducts(data);
	}


	@Override
	public List<ProductsVO.Products> getProductsList() {
		return dao.getProductsList();
	}


	@Override
	public int updateAreaName(String areaName, String contentid) {
		Map<String, String> map = new HashMap<>();
		map.put("areaName", areaName);
		map.put("contentid", contentid);
		return dao.updateAreaName(map);
	}


	@Override
	public List<ProductsVO.Products> getProductsPage(int page, int size) {
	    return dao.getProductsPage(page, size);
	}

	@Override
	public Products getProducts(String contentid) {
		return dao.getProducts(contentid);
	}

	@Override
	public int updateProductDetails(ProductsVO.Products product) {
	    return dao.updateProductDetails(product);
	}

	@Override
	public int updateProductInfo(ProductsVO.Products product) {
		return dao.updateProductInfo(product);
	}


	@Override
	public List<Products> searchProducts(String searchTerm) {
		return dao.searchProducts(searchTerm);
	}
	
	@Override
	public List<Products> getEventProducts() {
	    return dao.getEventProducts();
	}

	

}
