package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentItemUIConfiguration extends AbstractUIConfiguration<String> {

  private final IEquipmentTemplateProvider templateProvider;
  private final EquipmentTemplateTooltipBuilder tooltipBuilder;

  public EquipmentItemUIConfiguration(IEquipmentTemplateProvider provider, Resources resources) {
    templateProvider = provider;
    tooltipBuilder = new EquipmentTemplateTooltipBuilder(resources);
  }

  @Override
  protected String labelForExistingValue(String value) {
    return value;
  }

  @Override
  protected void configureTooltipForExistingValue(String value, ConfigurableTooltip configurableTooltip) {
    IEquipmentTemplate template = templateProvider.loadTemplate(value);
    if (template == null) {
      configurableTooltip.showNoTooltip();
    } else {
      tooltipBuilder.configureTooltip(template, configurableTooltip);
    }
  }
}