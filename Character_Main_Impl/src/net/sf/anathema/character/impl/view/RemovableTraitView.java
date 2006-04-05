package net.sf.anathema.character.impl.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.AbstractTraitView;
import net.sf.anathema.character.view.IBackgroundView;

public class RemovableTraitView extends AbstractTraitView implements IBackgroundView {

  private Component label;
  private final JButton button;
  private JPanel traitPanel;

  // Blatant abuse of labelText
  public RemovableTraitView(
      IIntValueDisplayFactory configuration,
      String labelText,
      Icon buttonIcon,
      int value,
      int maxValue) {
    super(configuration, labelText, value, maxValue);
    this.button = new JButton(buttonIcon);
    button.setPreferredSize(new Dimension(buttonIcon.getIconWidth() + 4, buttonIcon.getIconHeight() + 4));
  }

  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    label = new JLabel(getLabelText());
    panel.add(label, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(getValueDisplay().getComponent());
    panel.add(button);
    panel.revalidate();
  }

  public void setDeleteEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public void addRemoveButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  public void delete() {
    traitPanel.remove(label);
    traitPanel.remove(getValueDisplay().getComponent());
    traitPanel.remove(button);
    traitPanel.revalidate();
  }
}