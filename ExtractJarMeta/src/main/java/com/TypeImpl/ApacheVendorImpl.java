package com.TypeImpl;

import java.io.File;

import com.cache.AppCache;
import com.constants.DirConstants;
import com.constants.FieldConstants;

public class ApacheVendorImpl extends AbstractApacheTypeVendor {

	@Override
	public boolean performTask() {
		
		File file = new File(AppCache.getInstance().getValueFromReqFeilds(DirConstants.EXTRACTDIR));

		File[] listFiles = file.listFiles();

		for(File subFile:listFiles) {
			if("META-INF".equalsIgnoreCase(subFile.getName())) {
				for(File subFile1:subFile.listFiles()) {
					
					if((subFile1.getName().toLowerCase().startsWith(FieldConstants.LICENSE))) {
						AppCache.getInstance().putValueToReqFeilds(FieldConstants.LICENSE, subFile1.getAbsolutePath());
					}
					
					if((subFile1.getName().toLowerCase().startsWith(FieldConstants.NOTICE))) {
						AppCache.getInstance().putValueToReqFeilds(FieldConstants.NOTICE, subFile1.getAbsolutePath());
					}
					
				}
			}
		}
		
		
		return true;
	}

}
