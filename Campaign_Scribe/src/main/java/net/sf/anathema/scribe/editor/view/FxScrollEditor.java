package net.sf.anathema.scribe.editor.view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import net.sf.anathema.platform.markdown.WikiText;
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
    titleDisplay = createNameField();
    content = createContentDisplay();
    pane = new BorderPane();
    pane.setTop(titleDisplay);
    pane.setCenter(content);
  }

  private TextField createNameField() {
    final TextField field = new TextField("");
    field.getStyleClass().add("scroll-title");
    field.addEventFilter(KeyEvent.KEY_RELEASED, event -> titleChanged.announce().textChanged(field.getText()));
    return field;
  }

  @Override
  public void setTitlePrompt(final String prompt) {
    titleDisplay.setPromptText(prompt);
  }

  @Override
  public void setWikiText(final WikiText text) {
    updateText(text);
  }

  @Override
  public void setTitle(final String title) {
    updateTitle(title);
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
    area.addEventFilter(KeyEvent.KEY_RELEASED, event -> contentChanged.announce().textChanged(area.getText()));
    return area;
  }

  public Node getNode() {
    return pane;
  }

  private void updateText(WikiText text) {
    if (text.getCanonicalText().equals(content.getText())) {
      return;
    }
    int caretPosition = content.getCaretPosition();
    content.setText(text.getCanonicalText());
    content.positionCaret(caretPosition);
  }

  private void updateTitle(String title) {
    if (titleDisplay.getText().equals(title)) {
      return;
    }
    titleDisplay.setText(title);
  }
}