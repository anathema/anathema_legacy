package net.sf.anathema.framework.presenter.action.about;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.initialization.FxApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.markdown.HtmlConverter;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;
import org.apache.commons.io.IOUtils;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;

public class AnathemaAboutAction implements Command {

  private final Resources resources;

  public AnathemaAboutAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void execute() {
    MigPane parent = new MigPane(new LC().fill().wrapAfter(1));
    Scene scene = new Scene(parent, 300, 400);
    final Stage aboutStage = new Stage();
    aboutStage.initOwner(FxApplicationFrame.getOwner());
    aboutStage.setResizable(false);
    aboutStage.setTitle(resources.getString("Help.AboutDialog.Title"));
    aboutStage.setScene(scene);
    showProgramTitle(parent);
    showVersion(parent);
    showCopyrightAndLicense(parent);
    showCredits(parent);
    showCloseButton(parent, aboutStage);
    aboutStage.showAndWait();
  }

  @SuppressWarnings("ConstantConditions")
  private void showCredits(MigPane parent) {
    try {
      InputStream content = getClass().getClassLoader().getResourceAsStream("about.md");
      URL stylesheet = getClass().getClassLoader().getResource("about.css");
      String markdownContent = IOUtils.toString(content);
      HtmlText htmlText = new HtmlConverter().convert(new WikiText(markdownContent));
      WebView webView = new WebView();
      webView.getEngine().setUserStyleSheetLocation(stylesheet.toExternalForm());
      webView.getEngine().loadContent(htmlText.getHtmlText());
      parent.add(webView);
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
    parent.add(new Label(version), new CC().dockNorth().alignX("center"));
  }

  private String getString(String key) {
    return resources.getString(key);
  }

  private void showProgramTitle(MigPane parent) {
    parent.add(new Label("Anathema"), new CC().dockNorth().alignX("center").pad("3"));
  }

  private void showCloseButton(MigPane parent, final Stage aboutStage) {
    Button close = new Button(getString("Help.AboutDialog.CloseButton"));
    close.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        aboutStage.close();
      }
    });
    parent.add(close, new CC().dockSouth().alignX("center"));
  }
}