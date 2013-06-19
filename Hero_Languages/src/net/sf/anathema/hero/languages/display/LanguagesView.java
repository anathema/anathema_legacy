package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface LanguagesView {

  IButtonControlledObjectSelectionView<Object> addSelectionView(String labelText, AgnosticUIConfiguration renderer, RelativePath addIcon);

  IOverviewCategory addOverview(String border);

  IRemovableEntryView addEntryView(RelativePath removeIcon, Trait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}