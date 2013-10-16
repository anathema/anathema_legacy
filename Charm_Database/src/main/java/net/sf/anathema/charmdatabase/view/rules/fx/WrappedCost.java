package net.sf.anathema.charmdatabase.view.rules.fx;

import net.sf.anathema.character.main.magic.basic.cost.Cost;
import net.sf.anathema.character.main.magic.basic.cost.IHealthCost;
import net.sf.anathema.hero.charms.display.tooltip.HealthCostStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.ICostStringBuilder;

public class WrappedCost<T extends Cost> {
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
