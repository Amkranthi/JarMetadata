package com.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.cache.AppCache;
import com.constants.DirConstants;

public class ZipUtil {
	
	public static void unzipJar() throws IOException {
		
		String destinationDir=AppCache.getInstance().getValueFromReqFeilds(DirConstants.EXTRACTDIR);
		String jarPath=AppCache.getInstance().getValueFromReqFeilds(DirConstants.BASEDIR)+File.separator+AppCache.getInstance().getValueFromReqFeilds(DirConstants.JARNAME);
		
		File file = new File(jarPath);
		
		try(JarFile jar = new JarFile(file)){
			// fist get all directories,
			// then make those directory on the destination Path
			for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
				JarEntry entry = (JarEntry) enums.nextElement();

				String fileName = destinationDir + File.separator + entry.getName();
				File f = new File(fileName);

				if (fileName.endsWith("/")) {
					f.mkdirs();
				}
			}

			//now create all files
			for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
				JarEntry entry = (JarEntry) enums.nextElement();

				String fileName = destinationDir + File.separator + entry.getName();
				File f = new File(fileName);

				if (!fileName.endsWith("/")) {
					InputStream is = jar.getInputStream(entry);
					FileOutputStream fos = new FileOutputStream(f);

					// write contents of 'is' to 'fos'
					while (is.available() > 0) {
						fos.write(is.read());
					}

					fos.close();
					is.close();
				}
			}
		}
	} 
	
	
}
