package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class EquipmentItemUIConfiguration extends AbstractUIConfiguration<String> {

  private final IEquipmentTemplateProvider templateProvider;
  private final EquipmentTemplateTooltipBuilder tooltipBuilder;

  public EquipmentItemUIConfiguration(IEquipmentTemplateProvider provider) {
    templateProvider = provider;
    tooltipBuilder = new EquipmentTemplateTooltipBuilder();
  }

  @Override
  public String getLabel(String value) {
    return value;
  }

  @Override
  public String getToolTipText(String value) {
    IEquipmentTemplate template = templateProvider.loadTemplate(value);
    if (template != null) {
      return tooltipBuilder.getTooltipDescription(template);
    }
    return NO_TOOLTIP;
  }
}