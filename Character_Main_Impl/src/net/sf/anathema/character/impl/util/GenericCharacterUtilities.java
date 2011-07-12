package net.sf.anathema.character.impl.util;

import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.model.ICharacterStatistics;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(ICharacterStatistics statistics) {
    return createGenericCharacter(statistics, null);
  }
  
  public static GenericCharacter createGenericCharacter(ICharacterStatistics statistics, IEquipmentModifiers stats) {
	    return new GenericCharacter(statistics, new ExperiencePointManagement(statistics), stats);
	  }
}