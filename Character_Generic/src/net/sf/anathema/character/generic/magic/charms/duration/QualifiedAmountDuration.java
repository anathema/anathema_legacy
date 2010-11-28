package net.sf.anathema.character.generic.magic.charms.duration;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class QualifiedAmountDuration extends ReflectionEqualsObject implements IDuration {

  private final String amount;
  private final String unit;

  public QualifiedAmountDuration(String amount, String unit) {
    Ensure.ensureArgumentNotNull(amount);
    Ensure.ensureArgumentNotNull(unit);
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

  public String getText(IStringResourceHandler resources) {
    String amountText = resources.getString("Charm.Amount." + getAmount()); //$NON-NLS-1$
    String unitText = resources.getString(getUnitKey());
    return resources.getString("Charm.QualifiedAmount", new Object[] { amountText, unitText }); //$NON-NLS-1$
  }

  private String getUnitKey() {
    try {
      if (Integer.parseInt(getAmount()) == 1) {
        return "Charm.Unit." + unit + ".Singular"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    catch (NumberFormatException e) {
      // Nothing to do
    }
    return "Charm.Unit." + unit + ".Plural"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}