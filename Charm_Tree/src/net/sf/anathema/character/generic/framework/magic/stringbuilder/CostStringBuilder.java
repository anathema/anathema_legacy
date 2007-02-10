package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.resources.IResources;

public class CostStringBuilder extends AbstractCostStringBuilder<ICost> {

  public CostStringBuilder(IResources resources, String key) {
    super(resources, key, key);
  }

  public CostStringBuilder(IResources resources, String singularKey, String pluralKey) {
    super(resources, singularKey, pluralKey);
  }

  @Override
  protected String getQualifiedValueString(ICost cost) {
    int intValue = Integer.parseInt(cost.getCost());
    return intValue
        + IMagicStringBuilderConstants.Space
        + getResources().getString(intValue == 1 ? getSingularKey() : getPluralKey());
  }
}