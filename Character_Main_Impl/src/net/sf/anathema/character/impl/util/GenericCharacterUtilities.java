package net.sf.anathema.character.impl.util;

import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.generic.GenericCharacter;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(ICharacterStatistics statistics) {
    return new GenericCharacter(statistics, new ExperiencePointManagement(statistics));
  }
}