package net.sf.anathema.character.change;

import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeOf(ChangeFlavor flavor);
}
