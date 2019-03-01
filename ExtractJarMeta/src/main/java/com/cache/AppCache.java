package com.cache;

import java.util.HashMap;
import java.util.Map;

public class AppCache {
	
	private static AppCache appCache=new AppCache();
	
	
	private Map<String, String> reqFeilds = new HashMap<String, String>();
	
	
	public static AppCache getInstance() {
		return appCache;
	}

	public Map<String, String> getReqFeilds() {
		return reqFeilds;
	}
	
	public String getValueFromReqFeilds(String key) {
		return this.reqFeilds.get(key);
	}
	
	public void putValueToReqFeilds(String key,String value) {
		this.reqFeilds.put(key,value);
	}
	
}
