package net.sf.anathema.framework.environment;

public interface Preferences {
  String PREFERENCE_NOT_SET = null;
  
  String getPreference(String key);
}
