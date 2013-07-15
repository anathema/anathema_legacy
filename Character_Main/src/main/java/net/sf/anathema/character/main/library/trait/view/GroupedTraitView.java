package net.sf.anathema.character.main.library.trait.view;

import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface GroupedTraitView {
  void startNewGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String labelText, int maxValue);
}
