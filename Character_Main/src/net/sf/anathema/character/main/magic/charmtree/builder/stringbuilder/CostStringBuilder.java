package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.general.ICost;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

public class CostStringBuilder extends AbstractCostStringBuilder<ICost> {

  public CostStringBuilder(Resources resources, String key) {
    super(resources, key, key);
  }

  public CostStringBuilder(Resources resources, String singularKey, String pluralKey) {
    super(resources, singularKey, pluralKey);
  }

  @Override
  protected String getQualifiedValueString(ICost cost) {
    int intValue = Integer.parseInt(cost.getCost());
    return intValue + TooltipBuilder.Space +
           (cost.isPermanent() ? getResources().getString("Magic.Cost.Permanent") + TooltipBuilder.Space : StringUtilities.EMPTY_STRING) +
           getResources().getString(intValue == 1 ? getSingularKey() : getPluralKey());
  }
}