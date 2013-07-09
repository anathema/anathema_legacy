package net.sf.anathema.hero.equipment.display;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.lang.StringUtilities;

public class EquipmentTemplateTooltipBuilder {
  private final String UNICODE_DOT = "\u25CF";
  private final String BREAKPOINT = "<br>";

  public String getTooltipDescription(IEquipmentTemplate template) {
    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    builder.append("<b>" + template.getName() + "</b>");
    if (template.getCost() != null) {
      String templateCost = template.getCost().toString().replace("*", UNICODE_DOT);
      builder.append(" (" + templateCost + ")");
    }
    builder.append(BREAKPOINT);
    IEquipmentStats[] statsSet = template.getStats();
    for (IEquipmentStats stats : statsSet) {
      builder.append((stats != statsSet[0] ? ", " : "") + stats.getId());
    }
    builder.append(BREAKPOINT);
    if (!template.getDescription().isEmpty()) {
      builder.append("<i>" +
                     StringUtilities.createFixedWidthParagraph(template.getDescription(), BREAKPOINT, 80) +
                     "</i>");
    }
    builder.append("</html>");
    return builder.toString();
  }
}
