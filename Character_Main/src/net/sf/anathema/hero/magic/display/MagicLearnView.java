package net.sf.anathema.hero.magic.display;

import net.sf.anathema.lib.control.ChangeListener;

import java.util.List;

public interface MagicLearnView {

  void setAvailableMagic(List magics);

  void setLearnedMagic(List magics);

  void addMagicViewListener(MagicViewListener listener);

  boolean hasMoreThanOneElementLearned();

  boolean hasAnyElementLearned();

  void addLearnedListListener(ChangeListener changeListener);
}