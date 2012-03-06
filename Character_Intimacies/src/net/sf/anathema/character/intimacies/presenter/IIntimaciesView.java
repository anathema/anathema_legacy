package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.selection.IRemovableStringEntriesView;

public interface IIntimaciesView extends IRemovableStringEntriesView<IToggleButtonTraitView< ? >> {

  IOverviewCategory createOverview(String borderLabel);

  void setOverview(IOverviewCategory overviewView);
}