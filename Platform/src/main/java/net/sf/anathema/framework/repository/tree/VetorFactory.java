package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.gui.list.veto.Vetor;

public interface VetorFactory {
  Vetor createVetor(String message, String title);
}