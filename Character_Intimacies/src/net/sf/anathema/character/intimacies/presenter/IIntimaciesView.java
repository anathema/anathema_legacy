package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.file.RelativePath;

public interface IIntimaciesView {

  IStringSelectionView addSelectionView(String labelText, RelativePath addIcon);

  IOverviewCategory createOverview(String borderLabel);

  void setOverview(IOverviewCategory overviewView);

  void initGui(IIconToggleButtonProperties properties);

  IRemovableTraitView<IToggleButtonTraitView<?>> addEntryView(RelativePath removeIcon, Trait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}