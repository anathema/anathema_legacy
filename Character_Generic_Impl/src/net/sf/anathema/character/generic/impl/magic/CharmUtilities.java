package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

public class CharmUtilities {

  public static final String FIRST_EXCELLENCY = ".1stExcellency"; //$NON-NLS-1$
  public static final String SECOND_EXCELLENCY = ".2ndExcellency"; //$NON-NLS-1$
  public static final String THIRD_EXCELLENCY = ".3rdExcellency"; //$NON-NLS-1$
  public static final String INFINITE_MASTERY = ".InfiniteMastery"; //$NON-NLS-1$
  public static final String ESSENCE_FLOW = ".EssenceFlow"; //$NON-NLS-1$
  public static final String[] SOLAR_EXCELLENCIES = new String[] {
      CharacterType.SOLAR.getId() + FIRST_EXCELLENCY,
      CharacterType.SOLAR.getId() + SECOND_EXCELLENCY,
      CharacterType.SOLAR.getId() + THIRD_EXCELLENCY,
      CharacterType.SOLAR.getId() + INFINITE_MASTERY,
      CharacterType.SOLAR.getId() + ESSENCE_FLOW };

  public static boolean isGenericCharmFor(ICharm charm, ICharacterType type, IIdentificate groupId) {
    return isGenericCharmFor(charm, type) && charm.getId().endsWith(groupId.getId());
  }

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