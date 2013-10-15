package net.sf.anathema.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class TemplateTypeUiConfiguration extends AbstractUIConfiguration<HeroTemplate> {
  private Resources resources;

  public TemplateTypeUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(HeroTemplate value) {
    String newActionResource = value.getPresentationProperties().getNewActionResource();
    return resources.getString(newActionResource);
  }
}