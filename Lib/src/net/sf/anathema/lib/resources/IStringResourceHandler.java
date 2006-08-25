package net.sf.anathema.lib.resources;

public interface IStringResourceHandler {

  public boolean supportsKey(String key);

  public String getString(String key, Object... arguments);
}