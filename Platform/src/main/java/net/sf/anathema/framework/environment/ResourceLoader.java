package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.environment.resources.ResourceFile;

import java.util.Set;

public interface ResourceLoader {
  Set<ResourceFile> getResourcesMatching(String namePattern);
}