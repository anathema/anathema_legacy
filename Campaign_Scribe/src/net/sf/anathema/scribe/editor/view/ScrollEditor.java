package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.WikiText;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class ScrollEditor {

  private TextField titleDisplay;
  private TextArea content;
  private MigPane pane;
  private final Announcer<TextTypedListener> contentChanged = Announcer.to(TextTypedListener.class);
  private final Announcer<TextTypedListener> titleChanged = Announcer.to(TextTypedListener.class);

  public ScrollEditor() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        titleDisplay = createNameField();
        content = createContentDisplay();
        pane = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
        pane.add(titleDisplay, new CC().width("100%").grow());
        pane.add(content, new CC().push());
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

  public void whenContentTyped(TextTypedListener listener) {
    contentChanged.addListener(listener);
  }

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
