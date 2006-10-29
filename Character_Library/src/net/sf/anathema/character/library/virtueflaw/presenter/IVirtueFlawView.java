package net.sf.anathema.character.library.virtueflaw.presenter;

import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IVirtueFlawView extends IView {

  public ITextView addTextView(String label, int columnCount);

  public IObjectSelectionView<ITraitType> addVirtueFlawRootSelectionView(String string, ListCellRenderer renderer);

  public void setEnabled(boolean enabled);
}