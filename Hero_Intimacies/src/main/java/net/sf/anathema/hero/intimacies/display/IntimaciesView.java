package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.framework.library.overview.OverviewCategory;
import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface IntimaciesView {

  StringEntryView addSelectionView(String labelText);

  OverviewCategory addOverview(String border);

  void setOverview(OverviewCategory overviewView);

  ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue);
}