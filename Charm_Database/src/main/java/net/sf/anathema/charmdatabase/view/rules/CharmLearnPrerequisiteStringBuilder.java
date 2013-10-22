package net.sf.anathema.charmdatabase.view.rules;

import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisiteVisitor;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.AttributeKnownCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.DirectGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;

public class CharmLearnPrerequisiteStringBuilder {
	private final Resources resources;
	
	public CharmLearnPrerequisiteStringBuilder(Resources resources) {
		this.resources = resources;
	}
	
	public String getStringForPrerequisite(CharmLearnPrerequisite prerequisite) {
		final MagicDisplayLabeler labeler = new MagicDisplayLabeler(resources);
		final String[] label = new String[1];
		prerequisite.visitCharmLearnPrerequisite(new CharmLearnPrerequisiteVisitor() {

			@Override
			public void visitSimpleCharmPrerequisite(SimpleCharmLearnPrerequisite prerequisite) {
				label[0] = labeler.getLabelForMagic(prerequisite.getDirectPredecessors()[0]);
			}

			@Override
			public void visitDirectGroupCharmPrerequisite(DirectGroupCharmLearnPrerequisite prerequisite) {
				StringBuilder list = new StringBuilder();
				int length = prerequisite.getDirectPredecessors().length;
				for (int i = 0; i != length; i++) {
					if (i > 0 && length > 2) {
						list.append(", ");
					}
					if (i == length - 1) {
						list.append(" ");
						list.append(resources.getString("Joiner.Or"));
					}
					list.append(labeler.getLabelForMagic(prerequisite.getDirectPredecessors()[i]));
				}
				// TODO: May need to reinstate getThreshold
				/*if (prerequisite.getThreshold() > 1) {
					String pattern = resources.getString("Charms.DirectGroup.Pattern");
					label[0] = MessageFormat.format(pattern, prerequisite.getThreshold() + "", list.toString());
				} else*/ {
					label[0] = list.toString();
				}
			}

			@Override
			public void visitAttributeKnownCharmLearnPrerequisite(AttributeKnownCharmLearnPrerequisite prerequisite) {
				label[0] = resources.getString(prerequisite.getStringLabel());
			}
			
		});
		return label[0];
	}
}
