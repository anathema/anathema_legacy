package net.sf.anathema.charmdatabase.view.rules.fx;

import net.sf.anathema.charmdatabase.management.model.CharmPrerequisite;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.platform.fx.NodeHolder;

public class FxCharmPrerequisitesPanel extends AbstractFxListPanel<CharmPrerequisite> implements CharmPrerequisitesPanel, NodeHolder {
	
	public FxCharmPrerequisitesPanel(final MagicDisplayLabeler labeler) {
		super(new IconlessCellRenderer<CharmPrerequisite>() {
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
