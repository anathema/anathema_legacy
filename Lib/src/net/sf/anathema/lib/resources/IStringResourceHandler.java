package net.sf.anathema.lib.resources;

public interface IStringResourceHandler {

  boolean supportsKey(String key);

  String getString(String key, Object... arguments);
}