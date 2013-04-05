package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.list.veto.IVetor;

public interface IVetoableObjectSelectionView<V> extends IObjectSelectionView<V> {

  void addSelectionVetor(IVetor vetor);

  void removeSelectionVetor(IVetor vetor);
}