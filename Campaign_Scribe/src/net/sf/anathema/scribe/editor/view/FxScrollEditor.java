package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.WikiText;
import net.sf.anathema.scribe.editor.presenter.ScrollEditor;
import net.sf.anathema.scribe.editor.presenter.TextTypedListener;
import org.jmock.example.announcer.Announcer;

public class FxScrollEditor implements ScrollEditor {

  private TextField titleDisplay;
  private TextArea content;
  private BorderPane pane;
  private final Announcer<TextTypedListener> contentChanged = Announcer.to(TextTypedListener.class);
  private final Announcer<TextTypedListener> titleChanged = Announcer.to(TextTypedListener.class);

  public FxScrollEditor() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        titleDisplay = createNameField();
        content = createContentDisplay();
        pane = new BorderPane();
        pane.setTop(titleDisplay);
        pane.setCenter(content);
      }
    });
  }

  private TextField createNameField() {
    final TextField field = new TextField("");
    field.getStyleClass().add("scroll-title");
    field.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        titleChanged.announce().textChanged(field.getText());
      }
    });
    return field;
  }

  @Override
  public void setWikiText(final WikiText text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if (text.getCanonicalText().equals(content.getText())) {
          return;
        }
        content.setText(text.getCanonicalText());
      }
    });
  }

  @Override
  public void setTitle(final String title) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if (titleDisplay.getText().equals(title)) {
          return;
        }
        titleDisplay.setText(title);
      }
    });
  }

  @Override
  public void whenContentTyped(TextTypedListener listener) {
    contentChanged.addListener(listener);
  }

  @Override
  public void whenTitleTyped(TextTypedListener listener) {
    titleChanged.addListener(listener);
  }

  private TextArea createContentDisplay() {
    final TextArea area = new TextArea();
    area.setWrapText(true);
    area.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        contentChanged.announce().textChanged(area.getText());
      }
    });
    return area;
  }

  public Node getNode() {
    return pane;
  }
}
