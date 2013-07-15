package net.sf.anathema.hero.intimacies.display;

import javafx.scene.Node;
import net.sf.anathema.fx.hero.configurableview.FxConfigurableView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class FxStringEntryView implements StringEntryView {
  private final FxConfigurableView view = new FxConfigurableView();
  private final ITextView textView;

  public FxStringEntryView(String labelText) {
    this.textView = view.addLineView(labelText);
  }

  @Override
  public void addTextChangeListener(ObjectValueListener<String> listener) {
    textView.addTextChangedListener(listener);
  }

  public Tool addTool() {
    return view.addEditAction();
  }

  @Override
  public void clear() {
    textView.setText(null);
  }

  public Node getNode() {
    return view.getNode();
  }
}