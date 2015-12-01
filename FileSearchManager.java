package com.filesearch;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class FileSearchManager {

	private String inputString;
	private String directoryName;
	private File directoryObject;

	public FileSearchManager(String inputString, String directoryName) {
		this.inputString = inputString;
		this.directoryName = directoryName;
		this.directoryObject = new File(directoryName);
	}

	public Boolean directoryValidator() {
		Boolean validate = true;
		if(getDirectoryName().equalsIgnoreCase("")){
			validate = false;
		}

		if(!getDirectoryObject().isDirectory()) {
			validate = false;
		}
		return validate;		
	}

	public Boolean inputStringValidator() {
		Boolean validate = true;
		if(getInputString().equalsIgnoreCase("")) {
			validate = false;
		}
		return validate;
	}

	public void searchFile() {

		if(inputStringValidator()) {

			if(directoryValidator()) {
				try{	
					LinkedList<File> dirs = new LinkedList<File>();
					dirs.add(getDirectoryObject());					
					FileFilter filter = new WildcardFileFilter(getInputString());
					String recordCheck = "";

					while (dirs.size() > 0) {
						File dir = (File) dirs.removeLast();

						File[] matches = dir.listFiles(filter);

						if (matches != null) {
							for (int i = 0; i < matches.length; i++) {
								recordCheck +=matches[i];
								System.out.println(matches[i]);
							}
						}

						FileFilter dirFilter = new FileFilter() {
							public boolean accept(File pathname) {
								return pathname.isDirectory();
							}
						};
						File[] subDirs = dir.listFiles(dirFilter);

						if (subDirs != null) {
							for (int i = 0; i < subDirs.length; i++) {
								dirs.add(subDirs[i]);
							}
						}						
					}
					if(recordCheck.equals("")) {
						System.out.println("No records found");
						System.exit(1);
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}

			} else {
				System.err.println("Invalid Directory Path");
				System.exit(1);
			}
		} else {
			System.err.println("Please provide input string");	
			System.exit(1);
		}
	}

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public File getDirectoryObject() {
		return directoryObject;
	}

	public void setDirectoryObject(File directoryObject) {
		this.directoryObject = directoryObject;
	}
}
