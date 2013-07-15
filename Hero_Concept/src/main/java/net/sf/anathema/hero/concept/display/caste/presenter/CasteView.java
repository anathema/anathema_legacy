package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public interface CasteView {

  ObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer);
}