package net.sf.anathema.character.intimacies.presenter;

import javax.swing.Icon;

import net.sf.anathema.character.intimacies.view.IIntimaciesSelectionView;
import net.sf.anathema.character.intimacies.view.IOverviewView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryTabView;

public interface IIntimaciesView extends IRemovableEntryTabView<IRemovableTraitView<IToggleButtonTraitView>> {

  public IIntimaciesSelectionView addSelectionView(String labelText, Icon addIcon);

  public IOverviewView createOverview(String borderLabel);

  public void setOverview(IOverviewView overviewView);
}