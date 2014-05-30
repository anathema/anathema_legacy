package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.message.MessageType;

public interface UpdateView {
  void show();

  void showDescription(String message);

  void showLatestVersion(String versionNumber);

  void showInstalledVersion(Version installedVersion);
  
  void showNoMessage();

  void showMessage(String message, MessageType type);

  void showChangelog(String text);

  void enableUpdate();

  void disableUpdate();

  void showFilesToDownload(int numberOfElements);

  void showExpectedFileSize(int intSize);

  void showProgressOnFile(int progress);

  void showFilesAlreadyLoaded(int elementsHandled);

  void showProgressMessage(String string);

  Tool addTool();

  void refresh();
}
