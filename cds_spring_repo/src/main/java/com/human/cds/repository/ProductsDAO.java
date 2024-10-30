package com.human.cds.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.human.cds.vo.ProductsVO;
import com.human.cds.vo.ProductsVO.Products;

@Repository
public class ProductsDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String MAPPER = "com.human.cds.mapper.ProductsMapper";

    public int insertProducts(ProductsVO data) {
    	int result = 0;
    		
        try {
        		List<Products> items = data.getResponse().getBody().getItems().getItem();
          
        	
                for (Products products : items){
                	result += sqlSession.insert(MAPPER + ".insertProducts", products);
                }
            
        } catch (Exception e) {
        	System.out.println("insertProducts DAO오류");
            e.printStackTrace();
        }
        return result;
        
    }

	public List<Products> getProductsList() {
		
		List<Products> productsList = null;
		
		try {
			productsList = sqlSession.selectList(MAPPER+".getProductsList");
			
		} catch (Exception e) {
			System.out.println("getProductsList DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return productsList;
	}

	public int updateAreaName(Map<String, String> map) {
		int result = 0;
		
		try {
			result = sqlSession.update(MAPPER+".updateAreaName", map);
			
		} catch (Exception e) {
			System.out.println("updateAreaName DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<ProductsVO.Products> getProductsPage(int page, int size) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("offset", (page - 1) * size);
	    params.put("limit", size);
	    return sqlSession.selectList(MAPPER + ".getProductsPage", params);
	}

	public Products getProducts(String contentid) {
		
		Products vo = null;
		
		try {
			vo = sqlSession.selectOne(MAPPER+".getProducts", contentid);
			
		} catch (Exception e) {
			System.out.println("getProducts DAO 동작 중 오류 발생");
		}
		
		return vo;
	}

	public int updateProductDetails(ProductsVO.Products product) {
	    int result = 0;
	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("info", product.getInfo());
	        params.put("opendate", product.getOpendate());
	        params.put("restdate", product.getRestdate());
	        params.put("price", product.getPrice());
	        params.put("usetime", product.getUsetime());
	        params.put("homepage", product.getHomepage());
	        params.put("overview", product.getOverview());
	        params.put("contentid", product.getContentid());

	        result = sqlSession.update(MAPPER + ".updateProductDetails", params);
	    } catch (Exception e) {
	        System.out.println("updateProductDetails DAO 동작 중 오류 발생");
	        e.printStackTrace();
	    }
	    return result;
	}

	
	
	public int updateProductInfo(ProductsVO.Products product) {
		int result = 0;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("infoname1", product.getInfoname1());
			params.put("infotext1", product.getInfotext1());
			params.put("infoname2", product.getInfoname2());
			params.put("infotext2", product.getInfotext2());
			params.put("infoname3", product.getInfoname3());
			params.put("infotext3", product.getInfotext3());
			params.put("infoname4", product.getInfoname4());
			params.put("infotext4", product.getInfotext4());
	        params.put("contentid", product.getContentid());
			
			result = sqlSession.update(MAPPER + ".updateProductInfo", params);
		} catch (Exception e) {
			System.out.println("updateProductInfo DAO 동작 중 오류 발생");
			e.printStackTrace();
		}
		return result;
	}

	public List<Products> searchProducts(String searchTerm) {
		List<Products> productsList = null;
	    try {
	        productsList = sqlSession.selectList(MAPPER + ".searchProducts", searchTerm);
	    } catch (Exception e) {
	        System.out.println("searchProducts DAO 동작 중 오류 발생");
	        e.printStackTrace();
	    }
	    return productsList;
	
	}

	public List<Products> getEventProducts() {
	    return sqlSession.selectList(MAPPER + ".getEventProducts");
	}

	
	

}
