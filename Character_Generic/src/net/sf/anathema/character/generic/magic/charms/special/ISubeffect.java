package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface ISubeffect extends Identified {

  boolean isLearned();

  boolean isCreationLearned();

  void addChangeListener(IChangeListener listener);

  void setLearned(boolean learned);

  void setCreationLearned(boolean creationLearned);

  void setExperienceLearned(boolean experienceLearned);
}