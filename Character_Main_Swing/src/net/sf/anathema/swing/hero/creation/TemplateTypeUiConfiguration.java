package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class TemplateTypeUiConfiguration extends AbstractUIConfiguration<ITemplateTypeAggregation> {
  private Resources resources;

  public TemplateTypeUiConfiguration(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(ITemplateTypeAggregation value) {
    return resources.getString(value.getPresentationProperties().getNewActionResource());
  }
}