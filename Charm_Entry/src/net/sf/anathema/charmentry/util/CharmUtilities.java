package net.sf.anathema.charmentry.util;

import net.sf.anathema.character.generic.type.CharacterType;

public class CharmUtilities {
  public static String createIDFromName(CharacterType type, String charmName) {
    charmName = createIdFromName(charmName);
    return type + "." + charmName; //$NON-NLS-1$
  }

  public static String createIdFromName(String charmName) {
    charmName = charmName.replaceAll(" of the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll(" of ", ""); //$NON-NLS-1$//$NON-NLS-2$
    charmName = charmName.replaceAll(" the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll("!", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll("#", ""); //$NON-NLS-1$//$NON-NLS-2$
    return charmName;
  }
}