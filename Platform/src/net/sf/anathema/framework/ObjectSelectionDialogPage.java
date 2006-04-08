package net.sf.anathema.framework;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.AbstractDialogPage;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;

public class ObjectSelectionDialogPage extends AbstractDialogPage {

  private JPanel content = new JPanel(new BorderLayout());
  private JComboBox comboBox;
  private final IObjectSelectionProperties properties;

  public ObjectSelectionDialogPage(Object[] objects, IObjectSelectionProperties properties) {
    super(properties.getDefaultMessage());
    this.properties = properties;
    this.comboBox = new JComboBox(objects);
  }

  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  public JComponent createContent() {
    content.add(comboBox, BorderLayout.NORTH);
    return content;
  }

  public String getTitle() {
    return properties.getTitle();
  }

  public Object getSelectedObject() {
    return comboBox.getSelectedItem();
  }
}