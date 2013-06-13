package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.ListCellRenderer;

public interface IVirtueFlawView extends IView {

  ITextView addTextView(String label, int columnCount);

  IObjectSelectionView<ITraitType> addVirtueFlawRootSelectionView(String string, ListCellRenderer renderer);

  SimpleTraitView addLimitValueView(String label, int value, int maxValue);

  void setEnabled(boolean enabled);
}