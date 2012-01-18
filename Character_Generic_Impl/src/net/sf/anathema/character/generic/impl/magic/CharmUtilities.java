package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.type.ICharacterType;

public class CharmUtilities {

  public static boolean isGenericCharmFor(ICharm charm, IGenericCharacter character) {
    IMagicStats[] genericCharmStats = character.getGenericCharmStats();
    String charmId = charm.getId();
    for (IMagicStats stat : genericCharmStats) {
      if (charmId.startsWith(stat.getName().getId())) {
        return true;
      }
    }
    return false;
  }
}