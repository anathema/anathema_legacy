package net.sf.anathema.character.generic.framework;

import net.sf.anathema.lib.resources.ResourceFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterTemplateExtensionResourceCache implements ICharacterTemplateExtensionResourceCache {

  private final Map<String, ResourceFile> templateResources = new HashMap<>();
  private final static String CUSTOM_PATH = "repository/custom/";

  public CharacterTemplateExtensionResourceCache(List<ResourceFile> templateResources) {
    for (ResourceFile file : templateResources) {
      String fileName = file.getFileName();
      if (fileName.contains(CUSTOM_PATH)) {
        fileName = fileName.substring(fileName.lastIndexOf(CUSTOM_PATH) + CUSTOM_PATH.length());
      }
      this.templateResources.put(fileName, file);
    }
  }

  @Override
  public ResourceFile getTemplateResource(String fileName) {
    return templateResources.get(fileName);
  }
}
