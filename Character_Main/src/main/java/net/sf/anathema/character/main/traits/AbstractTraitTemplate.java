package net.sf.anathema.character.main.traits;

public abstract class AbstractTraitTemplate implements ITraitTemplate {
  protected final ModificationType lowerable;
  protected final int startValue;

  public AbstractTraitTemplate(int startValue, ModificationType lowerable) {
    this.startValue = startValue;
    this.lowerable = lowerable;
  }

  @Override
  public final int getStartValue() {
    return startValue;
  }

  @Override
  public final ModificationType getModificationType() {
    return lowerable;
  }

  @Override
  public boolean isRequiredFavored() {
    return false;
  }
}