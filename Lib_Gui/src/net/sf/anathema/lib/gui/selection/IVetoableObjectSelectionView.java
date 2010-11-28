package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.list.veto.IVetor;

public interface IVetoableObjectSelectionView<V> extends IObjectSelectionView<V> {

  public void addSelectionVetor(IVetor vetor);

  public void removeSelectionVetor(IVetor vetor);
}