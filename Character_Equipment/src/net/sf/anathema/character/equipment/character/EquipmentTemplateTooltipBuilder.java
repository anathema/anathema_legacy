package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class EquipmentTemplateTooltipBuilder {
	private final String UNICODE_DOT = "\u25CF";
	
	public String getTooltipDescription(IEquipmentTemplate template) {
		  StringBuilder builder = new StringBuilder();
		  builder.append("<html>");
		  builder.append(template.getName());
		  if (template.getCost() != null) {
			builder.append(" (" + template.getCost().toString().replace("*", UNICODE_DOT) + ")");
		  }
		  builder.append("<br>");
		  if (!template.getDescription().isEmpty()) {
			builder.append(template.getDescription() + "<br>");
		  }
		  IEquipmentStats[] statsSet = template.getStats();
		  for (IEquipmentStats stats : statsSet) {
			builder.append((stats != statsSet[0] ? ", " : "") + stats.getId());
		  }
		  builder.append("</html>");
		  return builder.toString();
	  }
}
