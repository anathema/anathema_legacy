package net.sf.anathema.hero.magic.display;

import java.util.List;

public interface MagicLearnView {

  void setAvailableMagic(List magics);

  void setLearnedMagic(List magics);

  void addMagicViewListener(MagicViewListener listener);
}