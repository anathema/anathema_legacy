package net.sf.anathema.framework.environment;

import net.sf.anathema.lib.resources.Resources;

public interface Environment extends Resources {
  void handle(Throwable throwable, String message);
}