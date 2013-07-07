package net.sf.anathema.character.main.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IVirtueFlawView {

  ITextView addTextView(String label, int columnCount);

  IObjectSelectionView<TraitType> addVirtueFlawRootSelectionView(String string, AgnosticUIConfiguration renderer);

  IIntValueView addLimitValueView(String label, int value, int maxValue);

  void setEnabled(boolean enabled);
}