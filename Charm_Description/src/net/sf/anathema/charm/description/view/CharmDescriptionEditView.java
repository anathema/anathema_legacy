package net.sf.anathema.charm.description.view;

import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CharmDescriptionEditView implements IView {

  private final JLabel editor = new JLabel("Editor");
  private final JPanel editorPanel = new JPanel(new BorderLayout());

  public CharmDescriptionEditView() {
    editorPanel.add(editor, BorderLayout.CENTER);
  }

  @Override
  public JComponent getComponent() {
    return editorPanel;
  }

  public void setDescription(String description) {
    editor.setText(description);
  }
}
