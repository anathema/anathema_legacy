package net.sf.anathema.character.main.magic.charm.special;

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