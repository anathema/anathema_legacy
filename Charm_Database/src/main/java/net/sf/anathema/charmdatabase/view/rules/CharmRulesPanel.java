package net.sf.anathema.charmdatabase.view.rules;

import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.charmdatabase.presenter.CharmTypeUi;
import net.sf.anathema.charmdatabase.presenter.DurationUi;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;


public interface CharmRulesPanel {
	CharmPrerequisitesPanel addPrerequisitesPanel(String title);
	
	CharmTraitMinimumsPanel addTraitsPanel(String title);
	
	CharmCostsPanel addCostsPanel(String title);
	
	CharmKeywordsPanel addKeywordsPanel(String title);
	
	ObjectSelectionView<CharmType> addTypeView(String string, CharmTypeUi ui);
	
	ObjectSelectionView<Duration> addDurationView(String string, DurationUi ui);
}
