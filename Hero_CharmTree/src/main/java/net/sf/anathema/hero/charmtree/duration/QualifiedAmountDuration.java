package net.sf.anathema.hero.charmtree.duration;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.Resources;

public class QualifiedAmountDuration extends ReflectionEqualsObject implements IDuration {

  private final String amount;
  private final String unit;

  public QualifiedAmountDuration(String amount, String unit) {
    Preconditions.checkNotNull(amount);
    Preconditions.checkNotNull(unit);
    this.amount = amount;
    this.unit = unit;
  }

  public String getAmount() {
    return amount;
  }

  @Override
  public String getText(Resources resources) {
    String amountText = resources.getString("Charm.Amount." + getAmount());
    String unitText = resources.getString(getUnitKey());
    return resources.getString("Charm.QualifiedAmount", amountText, unitText);
  }

  private String getUnitKey() {
    try {
      if (Integer.parseInt(getAmount()) == 1) {
        return "Charm.Unit." + unit + ".Singular";
      }
    }
    catch (NumberFormatException e) {
      // Nothing to do
    }
    return "Charm.Unit." + unit + ".Plural";
  }
}