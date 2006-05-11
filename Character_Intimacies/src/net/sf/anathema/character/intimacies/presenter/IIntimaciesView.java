package net.sf.anathema.character.intimacies.presenter;

import javax.swing.Icon;

import net.sf.anathema.character.intimacies.view.IIntimaciesSelectionView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;

public interface IIntimaciesView extends IRemovableEntriesView<IRemovableTraitView<IToggleButtonTraitView>> {

  public IIntimaciesSelectionView addSelectionView(String labelText, Icon addIcon);

  public IOverviewCategory createOverview(String borderLabel);

  public void setOverview(IOverviewCategory overviewView);
}