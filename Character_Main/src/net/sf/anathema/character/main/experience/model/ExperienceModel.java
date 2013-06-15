package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public interface ExperienceModel extends CharacterModel {

  static final Identified ID = new Identifier("Experience");

  IExperiencePointConfiguration getExperiencePoints();

  boolean isExperienced();

  void setExperienced(boolean experienced);
}
