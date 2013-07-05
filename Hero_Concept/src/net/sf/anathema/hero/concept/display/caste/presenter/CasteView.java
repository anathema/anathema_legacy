package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface CasteView {

  IObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer);
}