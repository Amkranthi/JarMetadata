package com.process;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.cache.AppCache;
import com.constants.DirConstants;
import com.constants.FieldConstants;

public class ProcessUtil {

	public static void processInputParams() {
		
		//TODO process these feilds according to Input args
		String jarName="spring-core-4.0.4.RELEASE.jar";
		String baseDir="D:\\PlayGround\\Test";
		String extractDir=baseDir+"\\extracted";
		String destDir=baseDir+"\\dest";
	
		getAppCache().putValueToReqFeilds(DirConstants.JARNAME, jarName);
		getAppCache().putValueToReqFeilds(DirConstants.BASEDIR, baseDir);
		getAppCache().putValueToReqFeilds(DirConstants.EXTRACTDIR, extractDir);
		getAppCache().putValueToReqFeilds(DirConstants.DESTDIR, destDir);
		
		//Considering Vender as Apache
		//TODO Need to calculate the vendor
		getAppCache().putValueToReqFeilds(FieldConstants.VENDOR, "Apache");
	
	}

	public static void processManifest() throws IOException {
		try(JarFile ji=new JarFile(getAppCache().getValueFromReqFeilds(DirConstants.BASEDIR)+File.separator+getAppCache().getValueFromReqFeilds(DirConstants.JARNAME))){
			Manifest manifest = ji.getManifest();
	
			Attributes mainAttributes = manifest.getMainAttributes();
	
			for(Object obj:mainAttributes.keySet()) {
				if(obj!=null) {
					getAppCache().putValueToReqFeilds(obj.toString(), mainAttributes.getValue(obj.toString()));
				}
			}
		}
	}

	public static void performOutputDirTask() throws IOException {
		String dirStruct = getAppCache().getValueFromReqFeilds(DirConstants.DESTDIR) 
				+ File.separator +getAppCache().getValueFromReqFeilds(FieldConstants.VENDOR)
				+File.separator+getAppCache().getValueFromReqFeilds(FieldConstants.IMPLEMENTATION_TITLE)
				+File.separator+getAppCache().getValueFromReqFeilds(FieldConstants.IMPLEMENTATION_VERSION)+File.separator;
	
	
		String licDirStruct=dirStruct+"LIC"+File.separator;
		String binDirStruct=dirStruct+"BIN"+File.separator;
		
		File f = new File(licDirStruct);
	
		if (licDirStruct.endsWith(File.separator)) {
			f.mkdirs();
		}
		
		f = new File(binDirStruct);
	
		if (binDirStruct.endsWith(File.separator)) {
			f.mkdirs();
		}
	
		File noticeFile= new File(licDirStruct+FieldConstants.NOTICE_TXT);
	
		Path sorcePath=new File(getAppCache().getValueFromReqFeilds(FieldConstants.NOTICE)).toPath();
	
		Files.copy(sorcePath, noticeFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
	
		File licenseFile= new File(licDirStruct+FieldConstants.LICENSE_TXT);
	
		sorcePath=new File(getAppCache().getValueFromReqFeilds(FieldConstants.LICENSE)).toPath();
	
		Files.copy(sorcePath, licenseFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
	
		File jarFile= new File(binDirStruct+getAppCache().getValueFromReqFeilds(DirConstants.JARNAME));
	
		sorcePath=new File(getAppCache().getValueFromReqFeilds(DirConstants.BASEDIR)+File.separator+getAppCache().getValueFromReqFeilds(DirConstants.JARNAME)).toPath();
		Files.copy(sorcePath, jarFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}

	public static AppCache getAppCache() {
		return AppCache.getInstance();
	}

}
