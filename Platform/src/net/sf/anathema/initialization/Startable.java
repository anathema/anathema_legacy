package net.sf.anathema.initialization;

import net.sf.anathema.initialization.reflections.AnathemaReflections;

public interface Startable {
  void doStart(AnathemaReflections reflections) throws Exception;
}