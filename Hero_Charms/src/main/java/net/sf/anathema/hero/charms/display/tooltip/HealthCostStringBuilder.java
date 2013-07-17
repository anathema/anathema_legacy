package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.model.cost.IHealthCost;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class HealthCostStringBuilder extends AbstractCostStringBuilder<IHealthCost> {

  public HealthCostStringBuilder(Resources resources, String key) {
    super(resources, key, key);
  }

  public HealthCostStringBuilder(Resources resources, String singularKey, String pluralKey) {
    super(resources, singularKey, pluralKey);
  }

  @Override
  protected String getQualifiedValueString(IHealthCost cost) {
    int intValue = Integer.parseInt(cost.getCost());
    return intValue + TooltipBuilder.Space + getResources().getString(cost.getType().getId()) + TooltipBuilder.Space +
           getResources().getString(intValue == 1 ? getSingularKey() : getPluralKey());
  }
}