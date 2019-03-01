package com.app;

import java.io.IOException;

import com.IType.IVendor;
import com.factory.VendorFactory;
import com.process.ProcessUtil;
import com.zip.ZipUtil;

public class App {

	public static void main(String[] args) throws IOException {

		// process Input params and places it in AppCache like Source file and Dest Dir 
		ProcessUtil.processInputParams();

		//Will process Manifest file and put all in AppCache
		ProcessUtil.processManifest();

		//Unzip the Jar file
		ZipUtil.unzipJar();

		IVendor vendor;

		vendor=VendorFactory.getVendor();

		if(null!=vendor) {
			vendor.performTask();
		}

		//All Details are ready
		ProcessUtil.performOutputDirTask();

		System.out.println("DONE");

	}

}
