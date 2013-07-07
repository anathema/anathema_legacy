package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface VirtueFlawView {

  ITextView addTextView(String label, int columnCount);

  IObjectSelectionView<TraitType> addVirtueFlawRootSelectionView(String string, AgnosticUIConfiguration renderer);

  IIntValueView addLimitValueView(String label, int value, int maxValue);

  void setEnabled(boolean enabled);
}