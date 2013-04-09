package net.sf.anathema.platform.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;


public class FxTextView implements ITextView {

  public static FxTextView SingleLine(String label) {
    return new FxTextView(label, new TextField());
  }

  public static FxTextView MultiLine(String label) {
    return new FxTextView(label, new TextArea());
  }

  private final TextInputControl view;

  public FxTextView(String label, TextInputControl view) {
    this.view = view;
    view.setPromptText(label);
  }

  @Override
  public void addTextChangedListener(final ObjectValueListener<String> listener) {
    view.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
        listener.valueChanged(newValue);
      }
    });
  }

  @Override
  public void setEnabled(boolean enabled) {
    view.setDisable(!enabled);
  }

  @Override
  public void setText(String text) {
    view.setText(text);
  }

  public Node getNode() {
    return view;
  }
}