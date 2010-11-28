package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.resources.IResources;

public class HealthCostStringBuilder extends AbstractCostStringBuilder<IHealthCost> {

  public HealthCostStringBuilder(IResources resources, String key) {
    super(resources, key, key);
  }

  public HealthCostStringBuilder(IResources resources, String singularKey, String pluralKey) {
    super(resources, singularKey, pluralKey);
  }

  @Override
  protected String getQualifiedValueString(IHealthCost cost) {
    int intValue = Integer.parseInt(cost.getCost());
    return intValue
        + IMagicStringBuilderConstants.Space
        + getResources().getString(cost.getType().getId())
        + IMagicStringBuilderConstants.Space
        + getResources().getString(intValue == 1 ? getSingularKey() : getPluralKey());
  }
}