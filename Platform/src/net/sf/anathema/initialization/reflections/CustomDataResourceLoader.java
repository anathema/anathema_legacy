package net.sf.anathema.initialization.reflections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.ResourceFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomDataResourceLoader implements ResourceLoader {
  private final File customFolder;

  public CustomDataResourceLoader(RepositoryLocationResolver resolver) {
    this.customFolder = new File(resolver.resolve(), "custom");
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(final String namePattern) {
    Collection<File> customFiles = Lists.newArrayList(customFolder.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.matches(namePattern);
      }
    }));
    HashSet<ResourceFile> resourceFiles = Sets.newHashSet();
    for (File file : customFiles) {
      resourceFiles.add(new ExternalResourceFile(file));
    }
    return resourceFiles;
  }
}