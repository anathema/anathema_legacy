package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.lib.resources.IResources;

public class BackgroundInternationalizer {
  private static final String BACKGROUND_TYPE_RESOURCE_KEY_PREFIX = "BackgroundType.Name."; //$NON-NLS-1$
  private final IResources resources;

  public BackgroundInternationalizer(IResources resources) {
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