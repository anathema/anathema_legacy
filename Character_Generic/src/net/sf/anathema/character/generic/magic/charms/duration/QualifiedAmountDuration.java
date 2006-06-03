package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.IResources;

public class QualifiedAmountDuration extends ReflectionEqualsObject implements IDuration {

  private final String amount;
  private final String unit;

  public QualifiedAmountDuration(String amount, String unit) {
    this.amount = amount;
    this.unit = unit;
  }

  public String getAmount() {
    return amount;
  }

  public String getUnit() {
    return unit;
  }

  public void accept(IDurationVisitor visitor) {
    visitor.visitQualifiedAmountDuration(this);
  }

  public String getText(IResources resources) {
    String amountText = resources.getString("Charm.Amount." + getAmount().toLowerCase()); //$NON-NLS-1$
    String unitText = resources.getString(getUnitKey());
    return resources.getString("Charm.QualifiedAmount", new String[] { amountText, unitText }); //$NON-NLS-1$
  }

  private String getUnitKey() {
    try {
      if (Integer.parseInt(getAmount()) == 1) {
        return "Charm.Unit." + unit.toLowerCase() + ".Singular"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    catch (NumberFormatException e) {
      // Nothing to do
    }
    return "Charm.Unit." + unit.toLowerCase() + ".Plural"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}