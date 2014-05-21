package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface GroupedTraitView {
  void startNewGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String labelText, int maxValue);
}
