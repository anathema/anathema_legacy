package net.sf.anathema.hero.traits.model;

public class FriendlyValueChangeChecker implements ValueChangeChecker {

  @Override
  public boolean isValidNewValue(int value) {
    return true;
  }
}