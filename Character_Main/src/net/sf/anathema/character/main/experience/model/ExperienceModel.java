package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface ExperienceModel extends CharacterModel {

  static final Identifier ID = new SimpleIdentifier("Experience");

  IExperiencePointConfiguration getExperiencePoints();

  boolean isExperienced();

  void setExperienced(boolean experienced);

  void addStateChangeListener(IChangeListener listener);
}
