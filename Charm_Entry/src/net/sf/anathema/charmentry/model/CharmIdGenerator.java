package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.type.ICharacterType;

public class CharmIdGenerator {
  public static String createIDFromName(ICharacterType type, String charmName) {
    charmName = createIdFromName(charmName);
    return type + "." + charmName; //$NON-NLS-1$
  }

  private static String createIdFromName(String charmName) {
    charmName = charmName.replaceAll(" of the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll(" of ", ""); //$NON-NLS-1$//$NON-NLS-2$
    charmName = charmName.replaceAll(" the ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll("!", ""); //$NON-NLS-1$ //$NON-NLS-2$
    charmName = charmName.replaceAll("#", ""); //$NON-NLS-1$//$NON-NLS-2$
    return charmName;
  }
}
