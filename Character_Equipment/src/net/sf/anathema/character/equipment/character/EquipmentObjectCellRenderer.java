package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

public class EquipmentObjectCellRenderer implements TechnologyAgnosticUIConfiguration<String> {

  private final IEquipmentTemplateProvider templateProvider;
  private final EquipmentTemplateTooltipBuilder tooltipBuilder;

  public EquipmentObjectCellRenderer(IEquipmentTemplateProvider provider) {
    templateProvider = provider;
    tooltipBuilder = new EquipmentTemplateTooltipBuilder();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof EquipmentObjectCellRenderer;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public RelativePath getIconsRelativePath(String value) {
    return NO_ICON;
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