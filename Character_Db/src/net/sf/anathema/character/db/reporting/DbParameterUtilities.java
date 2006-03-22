package net.sf.anathema.character.db.reporting;

import java.util.Map;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class DbParameterUtilities {

  public static void fillInBreeding(IGenericCharacter character, Map<Object, Object> parameters) {
    for (IGenericTrait background : character.getBackgrounds()) {
      if (background.getType().getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
        parameters.put(ICharacterReportConstants.BREEDING_VALUE, background.getCurrentValue());
        return;
      }
    }
    parameters.put(ICharacterReportConstants.BREEDING_VALUE, 0);
  }
}