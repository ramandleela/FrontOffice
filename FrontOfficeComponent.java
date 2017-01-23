package com.bookstore.frontoffice.component;

// Java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Integer;

// Spring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// This Service
import com.bookstore.frontoffice.controller.FrontOfficeController;


@Component
public class FrontOfficeComponent {
	private static final Logger logger = LoggerFactory.getLogger(FrontOfficeComponent.class);
	private static final String ConfigURL = "http://localhost:8000/getValue/";
	private static final String MessageQProperty = "InventoryServiceUrl";
	private BookRecord bookRecord;
	
	@Autowired
	public FrontOfficeComponent (BookRecord BookRecord){
		this.bookRecord = bookRecord;
	}
	
	// Construct BookRecord from Json
	public BookRecord convertJsontoBookRecord(String json) {
		BookRecord bookRecord = new BookRecord();
		List<String> tokens = new ArrayList<>(Arrays.asList(json.split("=")));
		logger.info("Tokens " + tokens);
		for (int j = 0; j < tokens.size(); j++) {
			logger.info(j +"  " + tokens.get(j));
			if(j==1) {
				bookRecord.setId(new Integer(tokens.get(j).replace(", description", "")));
			}else if(j==2) {
				bookRecord.setDescription(tokens.get(j).replace(", price",""));
			}else if (j==3) {
				bookRecord.setPrice(tokens.get(j).replace(", quantity", ""));
			} else if (j==4) {
				bookRecord.setQuantity(new Integer(tokens.get(j).replace("}", "")));
			}
		}
		logger.info("BookRecord: " + bookRecord.toString());
		return bookRecord;	
	}
	
	public void initialize() {
		// Check InventoryQ Name
		RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> response = restTemplate.getForEntity(ConfigURL + MessageQProperty, String.class);
    	if(response != null && response.getStatusCode() == HttpStatus.OK) {
			String inventoryServiceUrl = response.getBody().toString();
			logger.info("Inventory Service Url: " + inventoryServiceUrl);
			if (inventoryServiceUrl.startsWith("http")) {
				FrontOfficeController.host = inventoryServiceUrl;
				logger.info("Inventory Service Url set");
			} else {
				logger.warn("Could not set Inventory Service Url");
			}
		} else {
			logger.warn("GET request for Inventory Service Url failed");
		}
		
	}
}
	

