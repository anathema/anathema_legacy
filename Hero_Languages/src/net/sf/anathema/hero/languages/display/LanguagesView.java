package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface LanguagesView {

  ObjectSelectionViewWithTool<Object> addSelectionView(String labelText, AgnosticUIConfiguration<Object> renderer);

  IOverviewCategory addOverview(String border);

  IRemovableEntryView addEntryView(RelativePath removeIcon, String string);

  void removeEntryView(IRemovableEntryView removableView);
}