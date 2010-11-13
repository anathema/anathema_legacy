package net.sf.anathema.character.library.trait;

public class FriendlyValueChangeChecker implements IValueChangeChecker {

  public boolean isValidNewValue(int value) {
    return true;
  }
}