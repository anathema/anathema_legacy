package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.lib.resources.Resources;

public class BackgroundInternationalizer {
  private static final String BACKGROUND_TYPE_RESOURCE_KEY_PREFIX = "BackgroundType.Name.";
  private final Resources resources;

  public BackgroundInternationalizer(Resources resources) {
    this.resources = resources;
  }

  public String getDisplayName(IBackgroundTemplate template) {
    if (template instanceof CustomizedBackgroundTemplate) {
      return template.getId();
    }
    return resources.getString(BACKGROUND_TYPE_RESOURCE_KEY_PREFIX + template.getId());
  }

  public String getPrefix() {
    return BACKGROUND_TYPE_RESOURCE_KEY_PREFIX;
  }
}