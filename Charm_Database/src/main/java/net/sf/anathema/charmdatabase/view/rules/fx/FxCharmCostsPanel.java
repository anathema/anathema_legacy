package net.sf.anathema.charmdatabase.view.rules.fx;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.main.magic.basic.cost.Cost;
import net.sf.anathema.character.main.magic.basic.cost.CostImpl;
import net.sf.anathema.character.main.magic.basic.cost.HealthCost;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmCostsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.tooltip.CostStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.HealthCostStringBuilder;
import net.sf.anathema.platform.fx.NodeHolder;

public class FxCharmCostsPanel extends AbstractFxListPanel<WrappedCost<?>> implements CharmCostsPanel, NodeHolder {

	private Resources resources;

	
	public FxCharmCostsPanel(final Resources resources) {
		super(new IconlessCellRenderer<WrappedCost<?>>() {
					@Override
					public String getLabel(WrappedCost<?> cost) {
						return cost != null ? cost.getString() : null;
					}
		});
		
		this.resources = resources;
	}

	@Override
	public void setCosts(ICostList costs) {
		// TODO: I do not like this code duplication at all; this logic does not belong in this module
		// Existing logic for deriving the string does not translate well to this purpose, as we will eventually
		// need to know which item is which in the list.
		// This duplication must be removed.
		List<WrappedCost<?>> costSet = new ArrayList<>();
		if (costs.getEssenceCost() != CostImpl.NULL_COST) {
			costSet.add(new WrappedCost<Cost>(costs.getEssenceCost(),
					new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes")));
		}
		if (costs.getHealthCost() != HealthCost.NULL_HEALTH_COST) {
			costSet.add(new WrappedCost<HealthCost>(costs.getHealthCost(),
					new HealthCostStringBuilder(resources, "CharmTreeView.ToolTip.HealthLevel", "CharmTreeView.ToolTip.HealthLevels")));
		}
		if (costs.getWillpowerCost() != CostImpl.NULL_COST) {
			costSet.add(new WrappedCost<Cost>(costs.getWillpowerCost(),
					new CostStringBuilder(resources, "WillpowerType.Name")));
		}
		if (costs.getXPCost() != CostImpl.NULL_COST) {
			costSet.add(new WrappedCost<Cost>(costs.getXPCost(),
					new CostStringBuilder(resources, "CharmTreeView.ToolTip.ExperiencePoint", "CharmTreeView.ToolTip.ExperiencePoints")));
		}
		setObjects(costSet.toArray(new WrappedCost[0]));
	}

}
