package net.sf.anathema.character.db.reporting.rendering;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.*;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.IAnimaTableRangeProvider;

public class AnimaTableProvider extends AnimaTableRangeProvider implements IAnimaTableRangeProvider {

  private static final String[] BREEDING_4_RANGES = new String[] { "1-2", "3-6", "7-9", "10-14", "15+" };  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$//$NON-NLS-5$
  private static final String[] BREEDING_5_RANGES = new String[] { "1", "2-5", "6-8", "9-13", "14+" };  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$//$NON-NLS-5$
  private static final String[] BREEDING_6_RANGES = new String[] { "0", "1-4", "5-7", "8-12", "13+" };  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$//$NON-NLS-5$

  @Override
  public String getRange(int level, IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return getBreeding6Range(level);
      case 5:
        return getBreeding5Range(level);
      case 4:
        return getBreeding4Range(level);
      default:
        return super.getRange(level, character);
    }
  }

  private String getBreeding6Range(int level) {
    return BREEDING_6_RANGES[level];
  }

  private String getBreeding5Range(int level) {
    return BREEDING_5_RANGES[level];
  }

  private String getBreeding4Range(int level) {
    return BREEDING_4_RANGES[level];
  }

  private int getBreedingLevel(IGenericCharacter character) {
    for (IGenericTrait background : character.getBackgrounds()) {
      if (background.getType().getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
        return background.getCurrentValue();
      }
    }
    return 0;
  }
}
