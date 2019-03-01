package com.factory;

import com.IType.IVendor;
import com.TypeImpl.ApacheVendorImpl;
import com.TypeImpl.GenericVendorImpl;
import com.cache.AppCache;
import com.constants.FieldConstants;

public class VendorFactory {
	
	enum test{
		APACHE("Apache") {
			public IVendor getObj() {
				return new ApacheVendorImpl();
			}
		},
		OTHER("Other") {
			public IVendor getObj() {
				return new GenericVendorImpl();
			}
		};
		public abstract IVendor getObj();
		private String vendorName;
		
		private  test(String vendorName) {
			this.vendorName=vendorName;
		}

		public String getVendorName() {
			return vendorName;
		}
	}

	public static IVendor getVendor() {
		
		IVendor vendor=null;
		String reqVendorName=AppCache.getInstance().getValueFromReqFeilds(FieldConstants.VENDOR);
		
		for (test testVal : test.values()) {
			if(testVal.getVendorName().equalsIgnoreCase(reqVendorName)) {
				vendor=testVal.getObj();
			}
		}
		return vendor;
	}
}
