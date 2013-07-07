package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface LanguagesView {

  ObjectSelectionViewWithTool<Object> addSelectionView(String labelText, AgnosticUIConfiguration<Object> renderer);

  OverviewCategory addOverview(String border);

  RemovableEntryView addEntryView(RelativePath removeIcon, String string);
}