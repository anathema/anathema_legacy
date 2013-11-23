package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public interface SheetPreferenceView {
  ObjectSelectionView<PageSize> addObjectSelectionView(String title, AgnosticUIConfiguration<PageSize> uiConfiguration);
}
