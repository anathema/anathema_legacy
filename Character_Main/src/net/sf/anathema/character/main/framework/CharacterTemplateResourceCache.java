package net.sf.anathema.character.main.framework;

import net.sf.anathema.lib.resources.ResourceFile;

import java.util.List;
import java.util.Map;

public class CharacterTemplateResourceCache implements ICharacterTemplateResourceCache {

  private final Map<String, List<ResourceFile>> templateResources;

  public CharacterTemplateResourceCache(Map<String, List<ResourceFile>> templateResources) {
    this.templateResources = templateResources;
  }

  @Override
  public ResourceFile[] getTemplateResourcesForType(String type) {
    if (!templateResources.containsKey(type)) {
      return new ResourceFile[0];
    }
    List<ResourceFile> resourceFiles = templateResources.get(type);
    return resourceFiles.toArray(new ResourceFile[resourceFiles.size()]);
  }
}