package net.sf.anathema.character.main.model.experience;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface ExperienceModel  {

  static final Identifier ID = new SimpleIdentifier("Experience");

  IExperiencePointConfiguration getExperiencePoints();

  boolean isExperienced();

  void setExperienced(boolean experienced);

  void addStateChangeListener(ChangeListener listener);
}
