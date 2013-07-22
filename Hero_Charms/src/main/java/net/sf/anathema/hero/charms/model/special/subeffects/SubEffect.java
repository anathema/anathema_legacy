package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

public interface SubEffect extends Identifier {

  boolean isLearned();

  boolean isCreationLearned();

  void addChangeListener(ChangeListener listener);

  void setLearned(boolean learned);

  void setCreationLearned(boolean creationLearned);

  void setExperienceLearned(boolean experienceLearned);
}