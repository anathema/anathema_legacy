package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import java.nio.file.Path;

public interface RepositoryPreferenceView {
  ITextView addRepositoryDisplay(String label);

  Tool addTool();

  void selectNewRepository(String prompt);

  void whenRepositoryChangeIsRequested(ObjectValueListener<Path> objectValueListener);

  void showInExplorer(Path repositoryPath);

  boolean canOpenInExplorer();
}
