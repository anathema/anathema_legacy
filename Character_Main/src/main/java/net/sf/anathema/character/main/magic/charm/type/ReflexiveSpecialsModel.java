package net.sf.anathema.character.main.magic.charm.type;

public class ReflexiveSpecialsModel implements IReflexiveSpecialsModel {

  private final Integer primaryStep;
  private final Integer secondaryStep;

  public ReflexiveSpecialsModel(Integer primaryStep, Integer secondaryStep) {
    this.primaryStep = primaryStep;
    this.secondaryStep = secondaryStep;
  }

  @Override
  public Integer getPrimaryStep() {
    return primaryStep;
  }

  @Override
  public Integer getSecondaryStep() {
    return secondaryStep;
  }

  @Override
  public boolean isSplitEnabled() {
    return secondaryStep != null;
  }
}