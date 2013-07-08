package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;

public interface IntimaciesView {

  StringEntryView addSelectionView(String labelText, RelativePath addIcon);

  OverviewCategory addOverview(String border);

  void setOverview(OverviewCategory overviewView);

  ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue);
}