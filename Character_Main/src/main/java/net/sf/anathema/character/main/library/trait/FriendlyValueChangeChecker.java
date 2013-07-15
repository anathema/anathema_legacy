package net.sf.anathema.character.main.library.trait;

public class FriendlyValueChangeChecker implements ValueChangeChecker {

  @Override
  public boolean isValidNewValue(int value) {
    return true;
  }
}