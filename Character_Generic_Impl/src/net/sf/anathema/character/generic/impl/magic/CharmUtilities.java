package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.ICharacterType;

public class CharmUtilities {

  private static final String FIRST_EXCELLENCY = ".1stExcellency"; //$NON-NLS-1$
  private static final String SECOND_EXCELLENCY = ".2ndExcellency"; //$NON-NLS-1$
  private static final String THIRD_EXCELLENCY = ".3rdExcellency"; //$NON-NLS-1$
  private static final String INFINITE_MASTERY = ".InfiniteMastery"; //$NON-NLS-1$
  private static final String ESSENCE_FLOW = ".EssenceFlow"; //$NON-NLS-1$

  public static boolean isGenericCharmFor(ICharm charm, ICharacterType type) {
    String charmId = charm.getId();
    return charmId.startsWith(type.getId() + FIRST_EXCELLENCY)
        || charmId.startsWith(type.getId() + SECOND_EXCELLENCY)
        || charmId.startsWith(type.getId() + THIRD_EXCELLENCY)
        || charmId.startsWith(type.getId() + INFINITE_MASTERY)
        || charmId.startsWith(type.getId() + ESSENCE_FLOW);
  }

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