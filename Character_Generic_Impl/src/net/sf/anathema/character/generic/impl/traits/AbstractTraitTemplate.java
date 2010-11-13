package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;

public abstract class AbstractTraitTemplate implements ITraitTemplate {
  protected final LowerableState lowerable;
  protected final int startValue;
  private final int zeroValue;

  public AbstractTraitTemplate(ITraitTemplate defaultTemplate) {
    this(defaultTemplate.getStartValue(), defaultTemplate.getLowerableState(), defaultTemplate.getZeroLevelValue());
  }

  public AbstractTraitTemplate(int startValue, LowerableState lowerable, int zeroValue) {
    this.startValue = startValue;
    this.lowerable = lowerable;
    this.zeroValue = zeroValue;
  }

  public final int getStartValue() {
    return startValue;
  }

  public final LowerableState getLowerableState() {
    return lowerable;
  }

  public final int getZeroLevelValue() {
    return zeroValue;
  }

  public boolean isRequiredFavored() {
    return false;
  }
}