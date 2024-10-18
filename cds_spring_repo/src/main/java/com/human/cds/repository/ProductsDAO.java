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

    private SqlSession sqlSession;
    
    @Autowired
    public ProductsDAO(SqlSession sqlSession) {
    	this.sqlSession = sqlSession;
    }

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


	

}
