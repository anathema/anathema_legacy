package net.sf.anathema.hero.experience;

import net.sf.anathema.hero.advance.experience.ExperiencePointConfiguration;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface ExperienceModel extends HeroModel {

  static final Identifier ID = new SimpleIdentifier("Experience");

  ExperiencePointConfiguration getExperiencePoints();

  boolean isExperienced();

  void setExperienced(boolean experienced);

  void addStateChangeListener(ChangeListener listener);
}
