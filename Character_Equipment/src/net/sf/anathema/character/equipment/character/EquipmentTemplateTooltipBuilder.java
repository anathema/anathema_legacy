package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

public class EquipmentTemplateTooltipBuilder {
	private final String UNICODE_DOT = "\u25CF";
	private final String BREAKPOINT = "<br>";
	
	public String getTooltipDescription(IEquipmentTemplate template) {
		  StringBuilder builder = new StringBuilder();
		  builder.append("<html>");
		  builder.append("<b>" + template.getName() + "</b>");
		  if (template.getCost() != null) {
			builder.append(" (" + template.getCost().toString().replace("*", UNICODE_DOT) + ")");
		  }
		  builder.append(BREAKPOINT);
		  IEquipmentStats[] statsSet = template.getStats();
		  for (IEquipmentStats stats : statsSet) {
			builder.append((stats != statsSet[0] ? ", " : "") + stats.getId());
		  }
		  builder.append(BREAKPOINT);
		  if (!template.getDescription().isEmpty()) {
			builder.append("<i>" +
					AnathemaStringUtilities.createFixedWidthParagraph(template.getDescription(),
							BREAKPOINT, 80) +
						   "</i>");
		  }
		  builder.append("</html>");
		  return builder.toString();
	  }
}
