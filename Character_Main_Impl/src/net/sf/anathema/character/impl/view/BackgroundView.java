package net.sf.anathema.character.impl.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.AbstractTraitView;
import net.sf.anathema.character.model.traits.IDeleteListener;
import net.sf.anathema.character.view.IBackgroundView;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class BackgroundView extends AbstractTraitView implements IBackgroundView {

  private final GenericControl<IDeleteListener> listeners = new GenericControl<IDeleteListener>();
  private Component label;
  private Component button;
  private JPanel traitPanel;
  private final Icon buttonIcon;

  // Blatant abuse of labelText
  public BackgroundView(
      IIntValueDisplayFactory configuration,
      String labelText,
      Icon buttonIcon,
      int value,
      int maxValue) {
    super(configuration, labelText, value, maxValue);
    this.buttonIcon = buttonIcon;
  }

  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    label = new JLabel(getLabelText());
    panel.add(label, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(getValueDisplay().getComponent());
    button = new JButton(new AbstractAction(null, buttonIcon) {
      public void actionPerformed(ActionEvent e) {
        fireDeletionPerformed();
      }
    });
    button.setPreferredSize(new Dimension(buttonIcon.getIconWidth() + 4, buttonIcon.getIconHeight() + 4));
    panel.add(button);
    panel.revalidate();
  }

  public void setDeleteEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public void addDeleteListener(IDeleteListener listener) {
    listeners.addListener(listener);
  }

  private void fireDeletionPerformed() {
    listeners.forAllDo(new IClosure<IDeleteListener>() {
      public void execute(IDeleteListener input) {
        input.deletionPerformed();
      }
    });
  }

  public Component getComponent() {
    return label;
  }

  public void delete() {
    traitPanel.remove(label);
    traitPanel.remove(getValueDisplay().getComponent());
    traitPanel.remove(button);
    traitPanel.revalidate();
  }
}