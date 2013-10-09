package net.sf.anathema.charmdatabase.view.fx;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.basic.cost.Cost;
import net.sf.anathema.character.main.magic.basic.cost.CostImpl;
import net.sf.anathema.character.main.magic.basic.cost.HealthCost;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.basic.cost.IHealthCost;
import net.sf.anathema.charmdatabase.view.rules.CharmCostsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.tooltip.CostStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.HealthCostStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.ICostStringBuilder;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.platform.fx.ListSelectionView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmCostsPanel implements CharmCostsPanel {

	private MigPane pane;
	private Resources resources;
	
	private ListSelectionView<WrappedCost<?>> listView = new ListSelectionView<>();
	
	public FxCharmCostsPanel(final Resources resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
				pane.add(listView.getNode());
			}
		});
		
		this.resources = resources;
		
		listView.setCellRenderer(new AgnosticUIConfiguration<WrappedCost<?>>() {

			@Override
			public RelativePath getIconsRelativePath(WrappedCost<?> value) {
				return null;
			}

			@Override
			public String getLabel(WrappedCost<?> cost) {
				return cost != null ? cost.getString() : null;
			}

			@Override
			public void configureTooltip(WrappedCost<?> item,
					ConfigurableTooltip configurableTooltip) {
				// nothing to do
			}
			
		});
	}
	
	public Node getNode() {
		return pane;
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
		listView.setObjects(costSet.toArray(new WrappedCost[0]));
	}
	
	private class WrappedCost<T extends Cost> {
		private final T cost;
		private final ICostStringBuilder<T> builder;
		
		public WrappedCost(T cost, ICostStringBuilder<T> builder) {
			this.cost = cost;
			this.builder = builder;
		}
		
		public WrappedCost(IHealthCost healthCost,
				HealthCostStringBuilder healthCostStringBuilder) {
			this.cost = (T) healthCost;
			this.builder = (ICostStringBuilder<T>) healthCostStringBuilder;
		}

		public Cost getCost() {
			return cost;
		}
		
		public String getString() {
			return builder.getCostString(cost);
		}
	}

}
