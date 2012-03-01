package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.Comparator;

public class ByCharacterType implements Comparator<IIdentificate> {

  @Override
  public int compare(IIdentificate o1, IIdentificate o2) {
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