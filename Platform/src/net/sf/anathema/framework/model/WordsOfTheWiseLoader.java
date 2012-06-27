package net.sf.anathema.framework.model;

import com.google.common.collect.Lists;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.resources.ResourceFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class WordsOfTheWiseLoader {
  private final ResourceLoader resourceLoader;

  public WordsOfTheWiseLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  public List<String> loadWordsOfTheWise() {
    Set<ResourceFile> resourceFiles = resourceLoader.getResourcesMatching(".*\\.wotw");
    List<String> wordsOfTheWise = Lists.newArrayList();
    for (ResourceFile file : resourceFiles) {
      InputStream stream = null;
      try {
        stream = file.getURL().openStream();
        List<String> strings = IOUtils.readLines(stream);
        wordsOfTheWise.addAll(strings);
      } catch (IOException e) {
        IOUtils.closeQuietly(stream);
      }
    }
    return wordsOfTheWise;
  }
}