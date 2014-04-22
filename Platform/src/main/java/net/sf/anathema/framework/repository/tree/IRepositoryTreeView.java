package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.file.Extension;

import java.nio.file.Path;

public interface IRepositoryTreeView extends VetorFactory {

  Tool addTool();

  Path showSaveFile(String recommendedFileName, Extension defaultExtension);

  Path showLoadFile(Extension extension);

  AgnosticTree addAgnosticTree();
}