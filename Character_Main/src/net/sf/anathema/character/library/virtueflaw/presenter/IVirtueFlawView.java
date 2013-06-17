package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.ListCellRenderer;

public interface IVirtueFlawView {

  ITextView addTextView(String label, int columnCount);

  //TODO: Renderer
  IObjectSelectionView<TraitType> addVirtueFlawRootSelectionView(String string, ListCellRenderer renderer);

  IIntValueView addLimitValueView(String label, int value, int maxValue);

  void setEnabled(boolean enabled);
}