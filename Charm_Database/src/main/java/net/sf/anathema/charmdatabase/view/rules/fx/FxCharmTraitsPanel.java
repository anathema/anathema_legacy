package net.sf.anathema.charmdatabase.view.rules.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmTraitMinimumsPanel;
import net.sf.anathema.framework.environment.Resources;

public class FxCharmTraitsPanel extends AbstractFxListPanel<ValuedTraitType> implements CharmTraitMinimumsPanel {
	
	public FxCharmTraitsPanel(final Resources resources) {
		super(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200),
				new IconlessCellRenderer<ValuedTraitType>() {
			@Override
			public String getLabel(ValuedTraitType trait) {
				return trait != null ? resources.getString(trait.getType().getId()) + " "
						+ trait.getCurrentValue() : null;
			}
		});	}
	
	@Override
	public void setTraits(ValuedTraitType[] traits) {
		setObjects(traits);
	}

}
