package net.sf.anathema.character.main.framework;

import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.List;

public class CharacterTemplateResourceCache implements ICharacterTemplateResourceCache {

  private final List<ResourceFile> templateResources = new ArrayList<>();

  @Override
  public ResourceFile[] getTemplateResourcesForType() {
    return templateResources.toArray(new ResourceFile[templateResources.size()]);
  }

  public void add(ResourceFile resource) {
    templateResources.add(resource);
  }
}