package net.sf.anathema.character.main.hero.template;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterModelTemplateCache implements IExtensibleDataSet {

  private final List<ResourceFile> templateResources;
  private Map<Identifier, Object> templateById = new HashMap<>();

  public CharacterModelTemplateCache(List<ResourceFile> templateResources) {
    this.templateResources = templateResources;
  }

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
      if (file.getFileName().endsWith("/" + templateId.getId() + ".cmt")) {
        return file;
      }
    }
    throw new IllegalStateException("No resource found for templateId " + templateId.getId());
  }

  private <T> T loadTemplate(ResourceFile file, TemplateLoader<T> loader) {
    InputStream inputStream = null;
    try {
      inputStream = file.getURL().openStream();
      T template = loader.load(inputStream);
      return template;
    } catch (IOException e) {
      throw new AnathemaException(e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }
}
