package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISubeffect extends IIdentificate {

  boolean isLearned();

  boolean isCreationLearned();

  void addChangeListener(IChangeListener listener);

  void setLearned(boolean learned);

  void setCreationLearned(boolean creationLearned);

  void setExperienceLearned(boolean experienceLearned);
}