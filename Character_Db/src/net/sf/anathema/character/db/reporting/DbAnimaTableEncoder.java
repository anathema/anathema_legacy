package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class DbAnimaTableEncoder extends AnimaTableEncoder {

  public DbAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getFifthLevelRange(IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return "13+"; //$NON-NLS-1$
      case 5:
        return "14+"; //$NON-NLS-1$
      case 4:
        return "15+"; //$NON-NLS-1$
      default:
        return "16+"; //$NON-NLS-1$
    }
  }

  @Override
  protected String getFourthLevelRange(IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return "8-12"; //$NON-NLS-1$
      case 5:
        return "9-13"; //$NON-NLS-1$
      case 4:
        return "10-14"; //$NON-NLS-1$
      default:
        return "11-15"; //$NON-NLS-1$
    }
  }

  @Override
  protected String getThirdLevelRange(IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return "5-7"; //$NON-NLS-1$
      case 5:
        return "6-8"; //$NON-NLS-1$
      case 4:
        return "7-9"; //$NON-NLS-1$
      default:
        return "8-10"; //$NON-NLS-1$
    }
  }

  @Override
  protected String getSecondLevelRange(IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return "1-4"; //$NON-NLS-1$
      case 5:
        return "2-5"; //$NON-NLS-1$
      case 4:
        return "3-6"; //$NON-NLS-1$
      default:
        return "4-7"; //$NON-NLS-1$
    }
  }

  @Override
  protected String getFirstLevelRange(IGenericCharacter character) {
    switch (getBreedingLevel(character)) {
      case 6:
        return "0"; //$NON-NLS-1$
      case 5:
        return "1"; //$NON-NLS-1$
      case 4:
        return "1-2"; //$NON-NLS-1$
      default:
        return "1-3"; //$NON-NLS-1$
    }
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