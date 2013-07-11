package net.sf.anathema.hero.magic.display;

import net.sf.anathema.lib.util.Identifier;

import java.util.Comparator;
import java.util.List;

public interface MagicLearnView {

  void addMagicOptions(List<Identifier> magics, Comparator<Identifier> comparator);

  void setAvailableMagic(List magics);

  void removeMagicOptions(List magics);

  void addLearnedMagic(List magics);

  void setLearnedMagic(List magics);

  void removeLearnedMagic(Object[] magics);

  void addMagicViewListener(MagicViewListener listener);
}