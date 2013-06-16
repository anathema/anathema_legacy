package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;

public interface IIntimaciesView {

  IStringSelectionView addSelectionView(String labelText, RelativePath addIcon);

  IOverviewCategory addOverview(String border);

  void setOverview(IOverviewCategory overviewView);

  ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue);
}