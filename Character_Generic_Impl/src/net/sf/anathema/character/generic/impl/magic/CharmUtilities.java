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

  public static String createIDFromName(ICharacterType type, String charmName) {
    charmName = createIdFromName(charmName);
    return type + "." + charmName; //$NON-NLS-1$
  }

  private static String createIdFromName(String charmName) {
    charmName = charmName.replace(" of the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replace(" of ", ""); //$NON-NLS-1$//$NON-NLS-2$
    charmName = charmName.replace(" the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replace("!", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replace("#", ""); //$NON-NLS-1$//$NON-NLS-2$
    return charmName;
  }

}