package net.sf.anathema.character.library.trait;

public class FriendlyValueChangeChecker implements ValueChangeChecker {

  @Override
  public boolean isValidNewValue(int value) {
    return true;
  }
}