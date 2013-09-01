package net.sf.anathema.framework.environment.dependencies;

import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.environment.resources.ResourceFile;

import java.util.HashSet;
import java.util.Set;

public class AggregatedResourceLoader implements ResourceLoader {

  private final ResourceLoader[] delegates;

  public AggregatedResourceLoader(ResourceLoader... delegates) {
    this.delegates = delegates;
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    Set<ResourceFile> files = new HashSet<>();
    for (ResourceLoader loader : delegates) {
      files.addAll(loader.getResourcesMatching(namePattern));
    }
    return files;
  }
}