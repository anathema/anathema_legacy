package net.sf.anathema.initialization;

import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;

public interface Startable {
  void doStart(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws Exception;
}