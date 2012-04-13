package net.sf.anathema.initialization.reflections;

import java.net.URL;

public class AnathemaResource implements IAnathemaReflectionResource {
	String fileName;
	ClassLoader loader;
	
	public AnathemaResource(String fileName, ClassLoader loader) {
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

	@Override
	public ClassLoader getLoader() {
		return loader;
	}
	
}
