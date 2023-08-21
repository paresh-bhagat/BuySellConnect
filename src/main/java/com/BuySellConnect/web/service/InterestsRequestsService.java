package com.BuySellConnect.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.dto.InterestDto;
import com.BuySellConnect.web.dto.UserProductDto;
import com.BuySellConnect.web.entities.Interest;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.entities.UserProduct;
import com.BuySellConnect.web.repository.InterestRepository;
import com.BuySellConnect.web.repository.UserInfoRepository;
import com.BuySellConnect.web.repository.UserProductRepository;

@Service
public class InterestsRequestsService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Autowired
	private InterestRepository interestrepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private UserProductRepository productinforepo;
	
	@Autowired
    private emailService emailservice;
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private UserService userservice;
	
	// add interest product
	public int addInterestProduct(String username, String seller, String productId) {
		
		UserInfo temp = userinforepo.getUserByUserName(username);
		Interest interest = new Interest();
		interest.setInterestedProductId(Integer.parseInt(productId));
		interest.setProductSeller(seller);
		interest.setUserInfo(temp);
		interest.setInterestDate(new Date());
		
		temp.addInterestProduct(interest);
		
		this.userinforepo.save(temp);
		List<Interest> temp2 = this.interestrepo.findLatestInterestByUsername(username);
		int id = temp2.get(0).getInterestId();
		return id;
	}
	
	// delete interest
	public void deleteInterestProduct(String interestid) {
		
		this.interestrepo.deleteById(Integer.parseInt(interestid));
	}
	
	// sort Interest by date
	public static void sortInterestDtoByDate(List<InterestDto> interestsdto) {
        interestsdto.sort((interest1, interest2) -> 
        interest2.getInterestDate().compareTo(interest1.getInterestDate()));
    }
	
	// sort Interest by date
	public static void sortInterestByDate(List<Interest> interests) {
		interests.sort((interest1, interest2) -> 
		interest2.getInterestDate().compareTo(interest1.getInterestDate()));
	}
	
	// get all interest products
	public List<List<String>> getInterestProduct(String userName) {
		
		UserInfo temp = userinforepo.getUserByUserName(userName);
		List<InterestDto> interestdto =  new ArrayList<>();
		
		for(int i=0; i<temp.getMyinterests().size();i++)
			interestdto.add(this.modelMapper.map(temp.getMyinterests().get(i),InterestDto.class));
		
		sortInterestDtoByDate(interestdto);
		
		List<List<String>> features = new ArrayList<>();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		for(int i=0; i<interestdto.size(); i++) {
			List<String> tempstring = new ArrayList<>();
			tempstring.add(Integer.toString(interestdto.get(i).getInterestId())); //0
			tempstring.add(Integer.toString(interestdto.get(i).getInterestedProductId())); //1
			tempstring.add(interestdto.get(i).getProductSeller()); //2
			tempstring.add(formatDate.format(interestdto.get(i).getInterestDate())); //3
			
			UserProduct temp3 = this.productinforepo.findById(interestdto.get(i).getInterestedProductId()).get();
			UserProductDto productDto = this.modelMapper.map(temp3, UserProductDto.class);
			
			tempstring.add(productDto.getProductTitle()); //4
			String imagepath = "/productimages/" + productDto.getProductImage();
			
			tempstring.add(imagepath); //5
			features.add(tempstring);
		}
		
		return features;
		
	}
	
	// get all product requests
	public List<List<String>> getProductRequests(String userName) {
			
		List<Interest> interests = this.interestrepo.findByProductSeller(userName);
		//List<InterestDto> interestdto =  new ArrayList<>();
			
		//for(int i=0; i<interestdto.size();i++)
		//	interestdto.add(this.modelMapper.map(interestdto.get(i),InterestDto.class));
			
		sortInterestByDate(interests);
			
		List<List<String>> productRequests = new ArrayList<>();
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
			
		for(int i=0; i<interests.size(); i++) {
			List<String> tempstring = new ArrayList<>();
			tempstring.add(Integer.toString(interests.get(i).getInterestId())); //0
			tempstring.add(Integer.toString(interests.get(i).getInterestedProductId())); //1
			tempstring.add(interests.get(i).getUserInfo().getUsername()); //2
			tempstring.add(formatDate.format(interests.get(i).getInterestDate())); //3
				
			UserProduct temp3 = this.productinforepo.findById(interests.get(i).getInterestedProductId()).get();
			UserProductDto productDto = this.modelMapper.map(temp3, UserProductDto.class);
				
			tempstring.add(productDto.getProductTitle()); //4
			String imagepath = "/productimages/" + productDto.getProductImage();
				
			tempstring.add(imagepath); //5
			productRequests.add(tempstring);
		}
			
		return productRequests;
			
	}

	public void sendEmailNewInterestBuyerSeller(String buyer, String seller,String productId, int interestId) {
		UserProductDto productdto = this.productservice.getProductInfo(productId);
		UserInfo buyerinfo = this.userservice.getUserInfo(buyer);
		UserInfo sellerinfo = this.userservice.getUserInfo(seller);
		this.emailservice.sendInterestBuyer(productdto, buyerinfo, sellerinfo,Integer.toString(interestId));
		this.emailservice.sendInterestSeller(productdto, buyerinfo, sellerinfo,Integer.toString(interestId));
	}

	public void sendEmailCancelInterestBuyerSeller(String buyer, String seller, String interestId) {
		
		Interest temp = this.interestrepo.getById(Integer.parseInt(interestId));
		InterestDto temp2 = this.modelMapper.map(temp,InterestDto.class);
		UserProductDto productdto = this.productservice.getProductInfo(Integer.toString(temp2.getInterestedProductId()));
		
		UserInfo buyerinfo = this.userservice.getUserInfo(buyer);
		UserInfo sellerinfo = this.userservice.getUserInfo(seller);
		
		this.emailservice.sendCancelInterestBuyer(productdto, buyerinfo, sellerinfo,interestId);
		this.emailservice.sendCancelInterestSeller(productdto, buyerinfo, sellerinfo,interestId);
	}

	public void sendEmailCancelRequestBuyerSeller(String buyer, String seller, String interestId) {
		
		Interest temp = this.interestrepo.getById(Integer.parseInt(interestId));
		InterestDto temp2 = this.modelMapper.map(temp,InterestDto.class);
		UserProductDto productdto = this.productservice.getProductInfo(Integer.toString(temp2.getInterestedProductId()));
		
		UserInfo buyerinfo = this.userservice.getUserInfo(buyer);
		UserInfo sellerinfo = this.userservice.getUserInfo(seller);
		
		this.emailservice.sendCancelRequestBuyer(productdto, buyerinfo, sellerinfo,interestId);
		this.emailservice.sendCancelRequestSeller(productdto, buyerinfo, sellerinfo,interestId);
		
	}

}
