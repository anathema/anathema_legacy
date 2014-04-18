package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.list.veto.Vetor;

import java.nio.file.Path;

public interface IRepositoryTreeView {

  Tool addTool();

  Vetor createVetor(String message, String title);

  Path showSaveFile(String recommendedFileName, Extension defaultExtension);

  Path showLoadFile(Extension extension);

  AgnosticTree addAgnosticTree();
}