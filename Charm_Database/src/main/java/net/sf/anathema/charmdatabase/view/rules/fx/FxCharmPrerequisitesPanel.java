package net.sf.anathema.charmdatabase.view.rules.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.management.model.CharmPrerequisite;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;

public class FxCharmPrerequisitesPanel extends AbstractFxListPanel<CharmPrerequisite> implements CharmPrerequisitesPanel {
	
	public FxCharmPrerequisitesPanel(final MagicDisplayLabeler labeler) {
		super(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200),
				new IconlessCellRenderer<CharmPrerequisite>() {
					@Override
					public String getLabel(CharmPrerequisite charm) {
						return charm != null ? charm.getText(labeler) : null;
					}
		});
	}

	@Override
	public void setPrerequisites(CharmPrerequisite[] charms) {
		setObjects(charms);
	}

}
