package net.sf.anathema.framework.presenter.action.about;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.platform.fx.Stylesheet;
import net.sf.anathema.platform.markdown.HtmlConverter;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;
import org.apache.commons.io.IOUtils;
import org.controlsfx.dialog.Dialog;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;

import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class AnathemaAboutAction implements Command {

  private final Resources resources;
  private Stage stage;

  public AnathemaAboutAction(Resources resources, Stage stage) {
    this.resources = resources;
    this.stage = stage;
  }

  @Override
  public void execute() {
    MigPane parent = new MigPane(new LC().fill().wrapAfter(1));
    parent.getStyleClass().add("thinborder");
    new Stylesheet("skin/platform/aboutDialog.css").applyToParent(parent);
    final Dialog dialog = initializeDialogStage();
    showProgramTitle(parent);
    showVersion(parent);
    showCopyrightAndLicense(parent);
    showCredits(parent);
    dialog.setContent(parent);
    dialog.show();
  }

  private Dialog initializeDialogStage() {
    String title = resources.getString("Help.AboutDialog.Title");
    Dialog dialog = new Dialog(stage, title, false, NATIVE);
    dialog.getActions().addAll(Dialog.Actions.CLOSE);
    initCloseOnEscape(dialog);
    return dialog;
  }

  @SuppressWarnings("ConstantConditions")
  private void showCredits(MigPane parent) {
    try {
      InputStream content = getClass().getClassLoader().getResourceAsStream("about.md");
      URL stylesheet = getClass().getClassLoader().getResource("aboutPage.css");
      String markdownContent = IOUtils.toString(content);
      HtmlText htmlText = new HtmlConverter().convert(new WikiText(markdownContent));
      final WebView webView = new WebView();
      webView.getEngine().setUserStyleSheetLocation(stylesheet.toExternalForm());
      webView.getEngine().loadContent(htmlText.getHtmlText());
      parent.add(webView, new CC().pad(0, 2, 0, 2));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void showCopyrightAndLicense(MigPane parent) {
    String copyright = resources.getString("Help.AboutDialog.CopyrightLabel");
    String license = resources.getString("Help.AboutDialog.LicenseLabel");
    parent.add(new Label(copyright), new CC().dockNorth().alignX("center"));
    parent.add(new Label(license), new CC().dockNorth().alignX("center"));
  }

  private void showVersion(MigPane parent) {
    String versionNumber = getString("Anathema.Version.Numeric");
    String buildLabel = getString("Help.AboutDialog.BuiltLabel");
    String buildDate = getString("Anathema.Version.Built");
    String version = MessageFormat.format("v{0}, {1} {2}", versionNumber, buildLabel, buildDate);
    parent.add(new Label(version), new CC().dockNorth().alignX("center").pad("2"));
  }

  private String getString(String key) {
    return resources.getString(key);
  }

  private void showProgramTitle(MigPane parent) {
    parent.add(new Label("Anathema"), new CC().dockNorth().alignX("center").pad("2"));
  }

  private void initCloseOnEscape(final Dialog dialog) {
    dialog.getWindow().getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.ESCAPE), dialog::hide);
  }
}