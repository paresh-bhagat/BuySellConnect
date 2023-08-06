package com.BuySellConnect.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class otpService {
	
	public int[] createOtp() {
		int[] arr=new int [4];
		Random rand = new Random();
		arr[0] = rand.nextInt(10);
		arr[1] = rand.nextInt(10);
		arr[2] = rand.nextInt(10);
		arr[3] = rand.nextInt(10);
		return arr;
	}
	
	public Boolean sendOtpSms(String mobile_number,int[] otp) throws IOException {
		
		String apikey = "mieyhLkcvgSu2lFTH5Yx63NI9Et0ZjoQnBfwOC4XRWDpGMUsKJaw3CpzcfMStAIgGlkNKO6n0dXvm5B4";
		String message = String.valueOf(otp[0]) + String.valueOf(otp[1]) + String.valueOf(otp[2]) + String.valueOf(otp[3]) 
						+ " is your OTP for signup to BuySellConnect";
		message = URLEncoder.encode(message, "UTF-8");
			
		String fsturl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apikey +
		                "&message=" + message +
		                "&language=english&route=q&" +
		                "&numbers=" + mobile_number;
		
		// send GET request
		
		URL url = new URL(fsturl);
		HttpURLConnection con= (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0"); 
		con.setRequestProperty("cache-control", "no-cache");
		
		// get return code
		int code = con.getResponseCode();
		System.out.println("Response code: "+ code);
		
		// get response
		StringBuffer response=new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while(true) {

			String line = br.readLine();
			
			if(line==null)
				break;
			response.append(line);
		}
		
		System.out.println("Response code: "+ code);
		
		if(code!=200)
			return false;
		
		return true;
	}
	
	public int[] getOtp() {
		int[] otp = createOtp();
		return otp;
	}
}
