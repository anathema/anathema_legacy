package net.sf.anathema.framework.presenter.action.updatecheck;

import de.idos.updates.Version;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.controlsfx.dialog.Dialog;
import org.tbee.javafx.scene.layout.MigPane;

import static org.controlsfx.dialog.Dialog.Actions.OK;
import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class FxUpdateView implements UpdateView {

  private final Label latestVersionLabel = new Label();
  private final Button updateButton = new Button();
  private final Dialog dialog;
  private final ProgressBar updateProgress = new ProgressBar();
  private final ProgressBar fileProgress = new ProgressBar();
  private FxTextView changelogDisplay;
  private Label installedVersionLabel = new Label();
  private final String currentVersionText;
  private final String latestVersionText;
  private int sizeOfUpdate;
  private int sizeOfFile;
  private MigPane toolPane = new MigPane(LayoutUtils.fillWithoutInsets());

  public FxUpdateView(String title, String currentVersionText, String latestVersionText) {
    this.currentVersionText = currentVersionText;
    this.latestVersionText = latestVersionText;
    this.dialog = new Dialog(null, title, false, NATIVE);
    dialog.getActions().setAll(OK);
    this.changelogDisplay = FxTextView.MultiLine("", 10);
    changelogDisplay.setEnabled(false);
  }

  @Override
  public void show() {
    dialog.setContent(createContent());
    dialog.show();
  }

  private Node createContent() {
    MigPane pane = new MigPane(new LC().wrapAfter(2).insets("0", "0", "0", "15").fill());
    pane.add(new Label(currentVersionText));
    pane.add(installedVersionLabel);
    pane.add(new Label(latestVersionText));
    pane.add(latestVersionLabel);
    pane.add(new ScrollPane(changelogDisplay.getNode()), new CC().growX().spanX());
    pane.add(toolPane, new CC().spanY(2).grow());
    pane.add(updateProgress, new CC().grow().push());
    pane.add(fileProgress, new CC().grow().push());
    return pane;
  }

  @Override
  public void showDescription(String message) {
    dialog.setMasthead(message);
  }

  //Threading required!
  @Override
  public void showLatestVersion(String versionNumber) {
    FxThreading.runOnCorrectThread(() -> latestVersionLabel.setText(versionNumber));
  }

  @Override
  public void showInstalledVersion(Version installedVersion) {
    installedVersionLabel.setText(installedVersion.asString());
  }

  //Threading required!
  @Override
  public void showChangelog(String text) {
    FxThreading.runOnCorrectThread(() -> changelogDisplay.setText(text));
    
  }

  @Override
  public void enableUpdate() {
    updateButton.setDisable(false);
  }

  @Override
  public void disableUpdate() {
    updateButton.setDisable(true);
  }

  @Override
  public void showFilesToDownload(int numberOfElements) {
    this.sizeOfUpdate = numberOfElements;
  }

  @Override
  public void showExpectedFileSize(int intSize) {
    this.sizeOfFile = intSize;
  }

  @Override
  public void showProgressOnFile(int progress) {
    this.fileProgress.setProgress((double) progress / sizeOfFile);
  }

  @Override
  public void showFilesAlreadyLoaded(int elementsHandled) {
    this.updateProgress.setProgress((double) elementsHandled / sizeOfUpdate);
  }

  @Override
  public void showProgressMessage(String string) {
    //nothing to do
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    toolPane.add(tool.getNode());
    return tool;
  }

  @Override
  public void refresh() {
    //nothing to do
  }
}
