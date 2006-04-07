package net.sf.anathema.character.library.trait.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.intvalue.IRemovableIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class RearButtonTraitViewWrapper implements IRemovableIntValueView, ITraitView {

  private final JButton button;
  private ITraitView view;
  private JPanel traitViewPanel;
  private JPanel innerViewPanel;

  // Blatant abuse of labelText
  public RearButtonTraitViewWrapper(ITraitView view, Icon buttonIcon) {
    this.view = view;
    this.button = new JButton(buttonIcon);
    button.setPreferredSize(new Dimension(buttonIcon.getIconWidth() + 4, buttonIcon.getIconHeight() + 4));
  }

  public void addComponents(JPanel panel) {
    this.traitViewPanel = panel;
    this.innerViewPanel = new JPanel(new GridDialogLayout(2, false));
    view.addComponents(innerViewPanel);
    panel.add(innerViewPanel);
    panel.add(button);
    panel.revalidate();
  }

  public void setButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public void addButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  public void delete() {
    view.delete();
    traitViewPanel.remove(innerViewPanel);
    traitViewPanel.remove(button);
    traitViewPanel.revalidate();
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    view.removeIntValueChangedListener(listener);
  }

  public void setMaximum(int maximalValue) {
    view.setMaximum(maximalValue);
  }

  public void setValue(int newValue) {
    view.setValue(newValue);
  }
}