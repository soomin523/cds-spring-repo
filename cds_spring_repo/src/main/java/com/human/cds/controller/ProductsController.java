package com.human.cds.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.api.AreaCodeApiExplorer;
import com.human.cds.api.ProductsAreaBasedListApiExplorer;
import com.human.cds.service.ProductsService;
import com.human.cds.vo.AreaCodeVO;
import com.human.cds.vo.AreaCodeVO.AreaCode;
import com.human.cds.vo.ProductsVO;


@Controller
@RequestMapping("/products")
public class ProductsController {

   
    
    private String serviceKey = "FuwMUFZUntQUUD1GcyAcTAoz7EihmI5dxJj5Bd8FgRyKWqVr5ESL/j/cwogqgbuliXCqklKwumwuM60AZ5MHeQ==";
    private String srcUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	private String srcUrlAreaCode = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
    private String numOfRows = "50"; 
    		
    private ProductsService productsServiceImpl;

    @Autowired
    public ProductsController(ProductsService productsServiceImpl) {
    	this.productsServiceImpl = productsServiceImpl;
    }
    
    @GetMapping("/products.do")
    public String products(Model model) {
    			return "products/products";}
    
    
    @GetMapping("/insertProducts.do")
    @ResponseBody
    public String insertProducts(Model model) {
       
        try {
            int pageNo = 1;
            boolean morePages = true;
            int result = 0;
            
            Class<ProductsVO> vo = ProductsVO.class;
            
            while(true) {
                ProductsVO data = ProductsAreaBasedListApiExplorer.getApiJsonData(serviceKey, 
                		srcUrl, 
                        String.valueOf(pageNo), numOfRows, vo);
                
				if(data.getResponse().getBody().getItems().equals("")) break;
				morePages = data.getResponse().getBody().getItems().getItem().size() > 0;
				if(!morePages) break;
                
              
                List<ProductsVO.Products> filteredItems = data.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> 
                        (item.getCat2().equals("A0202") || item.getCat2().equals("A0203") || 
                         item.getCat2().equals("A0207") || item.getCat2().equals("A0208")) ||
                        (item.getCat1().equals("A03") || item.getCat3().equals("A04010700"))
                    )
                    .collect(Collectors.toList());
                
                data.getResponse().getBody().getItems().setItem(filteredItems);
                
                result += productsServiceImpl.insertProducts(data);
                
                pageNo++;
                
                if(pageNo > 400) {
                    break;
                }
            }
            
            if(result >= 1) {
                model.addAttribute("msg", "입력 성공");
            } else {
                model.addAttribute("msg", "목록을 저장하던 중 오류가 발생했습니다.");
            }
            
        } catch (Exception e) {
            System.out.println("insertProducts 컨트롤러 동작 중 오류 발생");
            e.printStackTrace();
            model.addAttribute("msg", "오류 발생: " + e.getMessage());
        }
        
        return "products/products";
    }

    
  //Products 지역 등록
  	@GetMapping("/getAreaName.do")
  	public String getAreaName(Model model) {
  		
  		try {
  			
  			final int[] result2 = {0};
  			
  			Class<AreaCodeVO> areavo = AreaCodeVO.class;
  			AreaCodeVO area = (AreaCodeVO)AreaCodeApiExplorer.getApiJsonData(serviceKey, srcUrlAreaCode, 
  						String.valueOf(1), numOfRows, "", areavo);
  			List<AreaCode> areacode = area.getResponse().getBody().getItems().getItem();
  			
  			Map<String, String> areaCodeMap = areacode.stream()
  				    .collect(Collectors.toMap(AreaCode::getCode, AreaCode::getName));
  			
  			
  			for(AreaCode item : areacode) {
  				try {
  					AreaCodeVO area2 = (AreaCodeVO)AreaCodeApiExplorer.getApiJsonData(serviceKey, srcUrlAreaCode, 
  							String.valueOf(1), numOfRows, item.getCode(), areavo);
  					List<AreaCode> area2code = area2.getResponse().getBody().getItems().getItem();
  					
  					for (AreaCode newItem : area2code) {
  						String codeKey = item.getCode()+"_"+newItem.getCode();
  			            areaCodeMap.put(codeKey, newItem.getName());
  			        }
  					
  				} catch (Exception e) {
  					System.out.println("지역 이름을 MAP에 저장하던 중 오류가 발생했습니다.");
  					e.printStackTrace();
  				}
  			}
  			List<ProductsVO.Products> productsList = productsServiceImpl.getProductsList();
  			
  			productsList.parallelStream()
  				.forEach(item -> {
  					String areaCode = item.getAreacode();
  					if(areaCode != null) {
  						String sigunguCode = areaCode+"_"+item.getSigungucode();
  						String areaName = areaCodeMap.get(areaCode);
  						String sigunguName = areaCodeMap.get(sigunguCode);
  						String contentid = item.getContentid();
  						
  						result2[0] += productsServiceImpl.updateAreaName(areaName, sigunguName, contentid);
  					}
  				});
  		} catch (Exception e) {
  			System.out.println("지역 이름을 저장하던 중 오류가 발생했습니다.");
  			e.printStackTrace();
  		}
  		
  		return "home";
  		
  	}
  	
  	
    @GetMapping("/getProductsList.do")
    public String getProducts(Model model) {
        List<ProductsVO.Products> productsList = productsServiceImpl.getProductsList();
		if(productsList != null) {
			 model.addAttribute("productsList", productsList);
			 return "products/products";
		}else {
			model.addAttribute("msg", "목록을 불러오던 중 오류가 발생했습니다.");
		}
        return "products/products";
    }
  	
    @GetMapping("/getMoreProducts")
    @ResponseBody
    public List<ProductsVO.Products> getMoreProducts(@RequestParam int page, @RequestParam int size) {
        return productsServiceImpl.getProductsPage(page, size);
    }

  	
  	
  	
  	
}
