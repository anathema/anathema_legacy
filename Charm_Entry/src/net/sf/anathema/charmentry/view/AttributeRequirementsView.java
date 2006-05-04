package net.sf.anathema.charmentry.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import net.sf.anathema.charmentry.presenter.IAttributeRequirementsView;

public class AttributeRequirementsView implements IAttributeRequirementsView {

  private final JPanel content = new JPanel();

  public JComponent getComponent() {
    content.setBorder(new TitledBorder("Charm Attributes")); //$NON-NLS-1$
    return content;
  }

  public JToggleButton addCheckBox(String label) {
    JCheckBox box = new JCheckBox(label);
    content.add(box);
    return box;
  }
}