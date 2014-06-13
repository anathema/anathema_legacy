package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import org.jmock.example.announcer.Announcer;


public class FxTextView implements ITextView {

  public static FxTextView SingleLine(String label) {
    return new FxTextView(label, new TextField());
  }

  public static FxTextView MultiLine(String label) {
    TextArea textArea = new TextArea();
    textArea.setWrapText(true);
    return new FxTextView(label, textArea);
  }

  public static FxTextView MultiLine(String label, int preferredRows) {
    TextArea textArea = new TextArea();
    textArea.setWrapText(true);
    textArea.setPrefRowCount(preferredRows);
    return new FxTextView(label, textArea);
  }

  private final TextInputControl view;
  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);

  @SuppressWarnings("unchecked")
  public FxTextView(String label, TextInputControl view) {
    this.view = view;
    view.setPromptText(label);
    view.textProperty().addListener((observableValue, s, newValue) -> announcer.announce().valueChanged(newValue));
  }

  @Override
  public void addTextChangedListener(ObjectValueListener<String> listener) {
    announcer.addListener(listener);
  }

  @Override
  public void setEnabled(boolean enabled) {
    view.setDisable(!enabled);
  }

  @Override
  public void setText(String text) {
    view.setText(text);
  }

  @Override
  public void removeAllListeners() {
    announcer.clear();
  }

  public Node getNode() {
    return view;
  }
}