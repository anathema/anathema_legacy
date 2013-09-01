package net.sf.anathema.character.main.framework;

import net.sf.anathema.framework.environment.resources.ResourceFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleCharacterTemplateCache implements CharacterTemplateResources {

  private final List<ResourceFile> templateResources = new ArrayList<>();

  public void add(ResourceFile resource) {
    templateResources.add(resource);
  }

  @Override
  public Iterator<ResourceFile> iterator() {
    return templateResources.iterator();
  }
}