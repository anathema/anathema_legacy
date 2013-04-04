package net.sf.anathema.lib.resources;

public interface Resources {

  boolean supportsKey(String key);

  String getString(String key, Object... arguments);
}