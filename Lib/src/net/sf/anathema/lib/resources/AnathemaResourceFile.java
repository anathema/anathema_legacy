package net.sf.anathema.lib.resources;

import java.net.URL;

public class AnathemaResourceFile implements IAnathemaResourceFile {
	String fileName;
	ClassLoader loader;
	
	public AnathemaResourceFile(String fileName, ClassLoader loader) {
		this.fileName = fileName;
		this.loader = loader;
	}

	@Override
	public URL getURL() {
		return loader.getResource(fileName);
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	public ClassLoader getLoader() {
		return loader;
	}
	
}
