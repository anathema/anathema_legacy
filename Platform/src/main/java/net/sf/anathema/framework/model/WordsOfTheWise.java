package net.sf.anathema.framework.model;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class WordsOfTheWise implements InformativeMessages {
  private final List<String> words;

  public WordsOfTheWise(ResourceLoader resourceLoader) {
    this.words = load(resourceLoader);
  }

  @Override
  public List<String> getAll() {
    return words;
  }

  private List<String> load(ResourceLoader resourceLoader) {
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