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
import com.human.cds.api.DetailInfoApiExplorer;
import com.human.cds.api.ProductsAreaBasedListApiExplorer;
import com.human.cds.api.ProductsDetailCommonApiExplorer;
import com.human.cds.api.ProductsIntroApiExplorer;
import com.human.cds.service.ProductsService;
import com.human.cds.vo.AreaCodeVO;
import com.human.cds.vo.AreaCodeVO.AreaCode;
import com.human.cds.vo.DetailCommonVO;
import com.human.cds.vo.DetailInfoVO;
import com.human.cds.vo.ProductsDetailIntroVO;
import com.human.cds.vo.ProductsDetailIntroVO.ProductsDetailIntro;
import com.human.cds.vo.ProductsVO;


@Controller
@RequestMapping("/products")
public class ProductsController {

   
    
    private String serviceKey = "NkFU7MXFQab71yXUMx3Qd0ZYVsBx2RMK81efMCHEI9/UvlLJQsGroUYdhxVKJhV858hn5n8GucOYfQ28eG9n3w==";
    private String srcUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	private String srcUrlAreaCode = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
	private String srcUrlDetailCommon = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
	private String srcUrlProductsIntro = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
	private String srcUrlDetailInfo = "http://apis.data.go.kr/B551011/KorService1/detailInfo1";
    private String numOfRows = "50"; 
    		
    @Autowired
    private ProductsService productsServiceImpl;

    
    @GetMapping("/products.do")
    public String products(Model model) {
 
        return "products/products";
    }
    
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
					        ((item.getCat2().equals("A0202") || item.getCat2().equals("A0203") || 
					          item.getCat2().equals("A0207") || item.getCat3().startsWith("A02080") || 
					          item.getCat1().equals("A03")) &&
					         !(item.getCat3().equals("A02020200") || item.getCat3().equals("A02020700")) &&
					         (item.getContenttypeid().equals("12") || item.getContenttypeid().equals("15") || item.getContenttypeid().equals("28")) && 
					         (item.getFirstimage() != null && !item.getFirstimage().isEmpty())&& 
					         (item.getAreacode() != null && !item.getAreacode().isEmpty()))
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
        
        return "home";
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
  						String areaName = areaCodeMap.get(areaCode);
  						String contentid = item.getContentid();
  						
  						result2[0] += productsServiceImpl.updateAreaName(areaName, contentid);
  					}
  				});
  		} catch (Exception e) {
  			System.out.println("지역 이름을 저장하던 중 오류가 발생했습니다.");
  			e.printStackTrace();
  		}
  		
  		return "home";
  		
  	}
  	
  	
    @GetMapping("/getProductsList")
    public String getProductsList(Model model) {
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

    
    @GetMapping("/updateProductInfo.do")
    public String updateProductInfo(Model model) {
    	try {
    		List<ProductsVO.Products> allProducts = productsServiceImpl.getProductsList();
             
             for (ProductsVO.Products product : allProducts) {
                 String contentid = product.getContentid();
                 String contenttypeid = product.getContenttypeid();
                 
                 Class<DetailInfoVO> infovo = DetailInfoVO.class;
                 
                 DetailInfoVO infodata = DetailInfoApiExplorer.getApiJsonData(serviceKey, srcUrlDetailInfo,
                         contentid, contenttypeid, infovo);

                     if (infodata.getResponse().getBody().getItems() == null ||
                             infodata.getResponse().getBody().getItems().getItem().isEmpty()) {
                        // System.out.println("No info items found for contentid: " + contentid);
                         continue;  // 비어 있으면 다음 제품으로 넘어갑니다.
                     }
                     
                     // Get the list of items from the response
                     List<DetailInfoVO.DetailInfo> infoItems = infodata.getResponse().getBody().getItems().getItem();
                     
                     for (DetailInfoVO.DetailInfo infoItem : infoItems) {
                         String serialnum = infoItem.getSerialnum();
 

                         switch (serialnum) {
                             case "0":
                                 product.setInfoname1(infoItem.getInfoname());
                                 product.setInfotext1(infoItem.getInfotext());
                                 break;
                             case "1":
                                 product.setInfoname2(infoItem.getInfoname());
                                 product.setInfotext2(infoItem.getInfotext());
                                 break;
                             case "2":
                                 product.setInfoname3(infoItem.getInfoname());
                                 product.setInfotext3(infoItem.getInfotext());
                                 break;
                             case "3":
                                 product.setInfoname4(infoItem.getInfoname());
                                 product.setInfotext4(infoItem.getInfotext());
                                 break;
                         }
                     }

 
    		
    	
    	 productsServiceImpl.updateProductInfo(product);
    	 }

        System.out.println("All product details have been updated successfully.");

    } catch (Exception e) {
        System.out.println("Error occurred while updating product details: " + e.getMessage());
        e.printStackTrace();
    }

    return "home";
}
    	
    
    @GetMapping("/updateProductDetails.do")
    public String updateProductDetails(Model model) {
        try {
            List<ProductsVO.Products> allProducts = productsServiceImpl.getProductsList();
            
            for (ProductsVO.Products product : allProducts) {
                String contentid = product.getContentid();
                String contenttypeid = product.getContenttypeid();
                
                Class<DetailCommonVO> dcomvo = DetailCommonVO.class;
                Class<ProductsDetailIntroVO> introvo = ProductsDetailIntroVO.class;
              

                // Fetch and set additional details
                DetailCommonVO comdata = ProductsDetailCommonApiExplorer.getApiJsonData(serviceKey, 
                                            srcUrlDetailCommon, contentid, contenttypeid, dcomvo);
                
                if (comdata.getResponse().getBody().getItems() == null ||
                        comdata.getResponse().getBody().getItems().getItem().isEmpty()) {
                        System.out.println("No items found for contentid: " + contentid);
                        continue;  // 비어 있으면 다음 제품으로 넘어갑니다.
                    }
                
                DetailCommonVO.Item item = comdata.getResponse().getBody().getItems().getItem().get(0);
                product.setHomepage(item.getHomepage());
                product.setOverview(item.getOverview());
                
               
                
                
                ProductsDetailIntroVO intdata = ProductsIntroApiExplorer.getApiJsonData(serviceKey, 
                                            srcUrlProductsIntro, contentid, contenttypeid, introvo);
                
                if (intdata.getResponse().getBody().getItems() == null ||
                        intdata.getResponse().getBody().getItems().getItem().isEmpty()) {
                 //System.out.println("No intro items found for contentid: " + contentid);
                        continue;  // 비어 있으면 다음 제품으로 넘어갑니다.
                    }
                
                ProductsDetailIntro intItem = intdata.getResponse().getBody().getItems().getItem().get(0);
                
		
		             // 정보가 있는지 체크하고, 해당 값이 존재하면 처리하도록 함
                	//레포츠
		             if (intItem.getInfocenterleports() != null) {
		                 product.setInfo(intItem.getInfocenterleports());
		             }
		             if (intItem.getOpenperiod() != null) {
		                 product.setOpendate(intItem.getOpenperiod());
		             }
		             if (intItem.getRestdateleports() != null) {
		                 product.setRestdate(intItem.getRestdateleports());
		             }
		             if (intItem.getUsefeeleports() != null) {
		                 product.setPrice(intItem.getUsefeeleports());
		             }
		             if (intItem.getUsetimeleports() != null) {
		                 product.setUsetime(intItem.getUsetimeleports());
		             }
		
		             // 축제
		             if (intItem.getProgram() != null) {
		                 product.setInfo(intItem.getProgram());
		             }
		             if (intItem.getEventstartdate() != null) {
		                 product.setOpendate(intItem.getEventstartdate());
		             }
		             if (intItem.getUsetimefestival() != null) {
		                 product.setPrice(intItem.getUsetimefestival());
		             }
		             if (intItem.getPlaytime() != null) {
		                 product.setUsetime(intItem.getPlaytime());
		             }
		
		             // 관광지
		             if (intItem.getExpguide() != null) {
		                 product.setInfo(intItem.getExpguide());
		             }
		             if (intItem.getOpendate() != null) {
		                 product.setOpendate(intItem.getOpendate());
		             }
		             if (intItem.getRestdate() != null) {
		                 product.setRestdate(intItem.getRestdate());
		             }
		      
		             if (intItem.getUsetime() != null) {
		                 product.setUsetime(intItem.getUsetime());
		             }

                // Update the product details in the database
                productsServiceImpl.updateProductDetails(product);
                
               // System.out.println("Product details updated successfully for contentid: " + contentid);
            }

            System.out.println("All product details have been updated successfully.");

        } catch (Exception e) {
            System.out.println("Error occurred while updating product details: " + e.getMessage());
            e.printStackTrace();
        }

        return "home";
    }

    
//    검색
    @GetMapping("/searchProducts")
    @ResponseBody
    public List<ProductsVO.Products> searchProducts(@RequestParam String searchTerm) {
        return productsServiceImpl.searchProducts(searchTerm);
    }

    //무작위 3개의 공연전시 데이터
    @GetMapping("/getEventProducts")
    @ResponseBody
    public List<ProductsVO.Products> getEventProducts() {
        return productsServiceImpl.getEventProducts();
    }

    @GetMapping("/getProducts")
    @ResponseBody
    public ProductsVO.Products getProducts(@RequestParam String contentid) {
        return productsServiceImpl.getProducts(contentid);
    }
  	
}
