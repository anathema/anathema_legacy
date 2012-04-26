package net.sf.anathema.magic.description.view;

import net.sf.anathema.lib.control.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.TextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MagicDescriptionEditView implements IView {

  private final TextView descriptionView = new AreaTextView(10, 1);
  private final JPanel editorPanel = new JPanel(new BorderLayout());

  public MagicDescriptionEditView() {
    editorPanel.add(descriptionView.getComponent(), BorderLayout.CENTER);
  }

  @Override
  public JComponent getComponent() {
    return editorPanel;
  }

  public void setDescription(String description) {
    descriptionView.setText(description);
  }

  public void addDescriptionChangeListener(IObjectValueChangedListener<String> listener) {
    descriptionView.addTextChangedListener(listener);
  }
}
