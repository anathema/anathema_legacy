package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.WikiText;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class ScrollEditor {

  private MigPane content;
  private TextArea textArea;
  private final Announcer<TextTypedListener> textChanged = Announcer.to(TextTypedListener.class);

  public ScrollEditor() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        textArea = createTextArea();
        content = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().grow().fill());
        content.add(textArea);
      }
    });
  }

  public void setWikiText(final WikiText text) {
    if (text.getCanonicalText().equals(textArea.getText())) {
      return;
    }
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        textArea.setText(text.getCanonicalText());
      }
    });
  }

  public void whenTextTyped(TextTypedListener listener) {
    textChanged.addListener(listener);
  }

  private TextArea createTextArea() {
    final TextArea area = new TextArea();
    area.setWrapText(true);
    area.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        textChanged.announce().textChanged(area.getText());
      }
    });
    return area;
  }

  public Node getNode() {
    return content;
  }
}
