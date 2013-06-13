package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.list.veto.Vetor;

public interface IVetoableObjectSelectionView<V> extends IObjectSelectionView<V> {

  void addSelectionVetor(Vetor vetor);

  @SuppressWarnings("UnusedDeclaration")
  void removeSelectionVetor(Vetor vetor);
}