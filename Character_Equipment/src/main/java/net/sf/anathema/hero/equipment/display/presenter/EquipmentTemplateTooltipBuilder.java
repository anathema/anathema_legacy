package net.sf.anathema.hero.equipment.display.presenter;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTemplateTooltipBuilder {
  private final Resources resources;

  public EquipmentTemplateTooltipBuilder(Resources resources) {
    this.resources = resources;
  }

  public void configureTooltip(IEquipmentTemplate template, ConfigurableTooltip tooltip) {
    tooltip.appendTitleLine(template.getName());
    if (template.getCost() != null) {
      int value = template.getCost().getValue();
      String valueRepresentation = getStringRepresentationForValue(value);
      String type = template.getCost().getType();
      String typeLabel = getStringRepresentationForType(type);
      tooltip.appendLine(typeLabel, valueRepresentation);
    }
    IEquipmentStats[] statsSet = template.getStatsList();
    List<String> statsIds = new ArrayList<>();
    for (IEquipmentStats stats : statsSet) {
      statsIds.add(stats.getId());
    }
    String stats = Joiner.on(", ").join(statsIds);
    tooltip.appendLine(stats);
    if (!template.getDescription().isEmpty()) {
      tooltip.appendDescriptiveLine(template.getDescription());
    }
  }

  private String getStringRepresentationForType(String type) {
    switch (type) {
      case "Resources":
        return resources.getString("Equipment.Cost.Type.Resources");
      case "Artifact":
        return resources.getString("Equipment.Cost.Type.Artifact");
      case "Manse":
        return resources.getString("Equipment.Cost.Type.Manse");
      default:
        throw new UnreachableCodeReachedException("Unknown Cost type: " + type);
    }
  }

  private String getStringRepresentationForValue(int value) {
    if (value == 0) {
      return resources.getString("Equipment.Cost.Value.None");
    }
    if (value == 6) {
      return resources.getString("Equipment.Cost.Value.NotApplicable");
    }
    String dot = resources.getString("Equipment.Cost.Value.Dot");
    return Strings.repeat(dot, value);
  }
}