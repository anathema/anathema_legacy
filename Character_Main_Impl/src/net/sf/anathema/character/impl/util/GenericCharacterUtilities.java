package net.sf.anathema.character.impl.util;

import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.model.ICharacterStatistics;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(ICharacterStatistics statistics) {
    return new GenericCharacter(statistics, new ExperiencePointManagement(statistics));
  }
}