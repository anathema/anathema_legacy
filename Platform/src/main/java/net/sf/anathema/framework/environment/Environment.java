package net.sf.anathema.framework.environment;

import net.sf.anathema.lib.exception.ExceptionHandler;
import net.sf.anathema.lib.resources.Resources;

public interface Environment extends Resources, ExceptionHandler {
  String getPreference(String key);
}