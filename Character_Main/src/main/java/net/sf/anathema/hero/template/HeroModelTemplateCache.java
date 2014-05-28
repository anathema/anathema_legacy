package net.sf.anathema.hero.template;

import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroModelTemplateCache implements ExtensibleDataSet {

  private final List<ResourceFile> templateResources = new ArrayList<>();
  private Map<Identifier, Object> templateById = new HashMap<>();

  public void add(ResourceFile resource) {
    templateResources.add(resource);
  }

  @SuppressWarnings("unchecked")
  public <T> T loadTemplate(Identifier templateId, TemplateLoader<T> loader) {
    if (templateById.containsKey(templateId)) {
      return (T) templateById.get(templateId);
    }
    ResourceFile file = getResourceFileFor(templateId);
    T template = loadTemplate(file, loader);
    templateById.put(templateId, template);
    return template;
  }

  private ResourceFile getResourceFileFor(Identifier templateId) {
    for (ResourceFile file : templateResources) {
      if (file.getFileName().endsWith("/" + templateId.getId() + ".template")) {
        return file;
      }
    }
    throw new IllegalStateException("No resource found for templateId " + templateId.getId());
  }

  private <T> T loadTemplate(ResourceFile file, TemplateLoader<T> loader) {
    InputStream inputStream = null;
    try {
      inputStream = file.getURL().openStream();
      return loader.load(inputStream);
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }
}
