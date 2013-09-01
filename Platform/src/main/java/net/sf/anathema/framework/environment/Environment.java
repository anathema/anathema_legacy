package net.sf.anathema.framework.environment;

public interface Environment extends Resources, ExceptionHandler {
  String getPreference(String key);
}