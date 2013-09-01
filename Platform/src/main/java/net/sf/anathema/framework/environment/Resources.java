package net.sf.anathema.framework.environment;

public interface Resources {

  boolean supportsKey(String key);

  String getString(String key, Object... arguments);
}