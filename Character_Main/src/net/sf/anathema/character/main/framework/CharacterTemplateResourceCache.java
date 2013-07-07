package net.sf.anathema.character.main.framework;

import net.sf.anathema.lib.resources.ResourceFile;

import java.util.List;
import java.util.Map;

public class CharacterTemplateResourceCache implements ICharacterTemplateResourceCache {

  private final Map<String, List<ResourceFile>> templateResources;
  private final static String CUSTOM_PATH = "repository/custom";

  public CharacterTemplateResourceCache(Map<String, List<ResourceFile>> templateResources) {
    this.templateResources = templateResources;
  }

  @Override
  public ResourceFile[] getTemplateResourcesForType(String type) {
    List<ResourceFile> resourceFiles = templateResources.get(type);
    return resourceFiles.toArray(new ResourceFile[resourceFiles.size()]);
  }

  @Override
  public boolean isCustomTemplate(ResourceFile resource) {
    //TODO: Search for a safer means to evaluate custom content
    return resource.getURL().toString().contains(CUSTOM_PATH);
  }
}
