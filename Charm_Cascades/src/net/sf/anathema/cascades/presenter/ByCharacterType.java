package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;

import java.util.Comparator;

public class ByCharacterType implements Comparator<Identified> {

  @Override
  public int compare(Identified o1, Identified o2) {
    boolean firstCharacterType = o1 instanceof ICharacterType;
    boolean secondCharacterType = o2 instanceof CharacterType;
    if (firstCharacterType) {
      if (secondCharacterType) {
        return ((ICharacterType) o1).compareTo((CharacterType) o2);
      }
      return -1;
    }
    if (secondCharacterType) {
      return 1;
    }
    return 0;
  }
}