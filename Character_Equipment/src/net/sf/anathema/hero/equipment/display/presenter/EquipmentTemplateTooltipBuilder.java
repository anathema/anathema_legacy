package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.lang.StringUtilities;

public class EquipmentTemplateTooltipBuilder {
  private static final String UNICODE_DOT = "\u25CF";
  private static final String BREAKPOINT = "<br>";

  public String getTooltipDescription(IEquipmentTemplate template) {
    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    builder.append("<b>").append(template.getName()).append("</b>");
    if (template.getCost() != null) {
      String templateCost = template.getCost().toString().replace("*", UNICODE_DOT);
      builder.append(" (").append(templateCost).append(")");
    }
    builder.append(BREAKPOINT);
    IEquipmentStats[] statsSet = template.getStats();
    for (IEquipmentStats stats : statsSet) {
      builder.append(stats != statsSet[0] ? ", " : "").append(stats.getId());
    }
    builder.append(BREAKPOINT);
    if (!template.getDescription().isEmpty()) {
      builder.append("<i>").append(StringUtilities.createFixedWidthParagraph(template.getDescription(), BREAKPOINT, 80)).append("</i>");
    }
    builder.append("</html>");
    return builder.toString();
  }
}
