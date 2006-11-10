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
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SpecialtyView extends AbstractTraitView implements ISpecialtyView {

  private final ChangeControl control = new ChangeControl();
  private final Component abilityLabel;
  private final Component separatorLabel = new JLabel("-"); //$NON-NLS-1$
  private final Component specialtyLabel;
  private JButton deleteButton;
  private JPanel traitPanel;
  private final Icon deleteIcon;

  public SpecialtyView(
      IIntValueDisplayFactory configuration,
      String labelText,
      Icon deleteIcon,
      String id,
      int value,
      int maxValue) {
    super(configuration, labelText, value, maxValue);
    this.deleteIcon = deleteIcon;
    specialtyLabel = new JLabel(id);
    abilityLabel = new JLabel(labelText);
  }

  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    panel.add(abilityLabel);
    panel.add(separatorLabel);
    panel.add(specialtyLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(getValueDisplay().getComponent());
    deleteButton = new JButton(new AbstractAction(null, deleteIcon) {
      public void actionPerformed(ActionEvent e) {
        fireDeletionPerformed();
      }
    });
    deleteButton.setPreferredSize(new Dimension(deleteIcon.getIconWidth() + 4, deleteIcon.getIconHeight() + 4));
    panel.add(deleteButton);
    panel.revalidate();
  }

  public void addDeleteListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireDeletionPerformed() {
    control.fireChangedEvent();
  }

  public void delete() {
    traitPanel.remove(abilityLabel);
    traitPanel.remove(separatorLabel);
    traitPanel.remove(specialtyLabel);
    traitPanel.remove(getValueDisplay().getComponent());
    traitPanel.remove(deleteButton);
    traitPanel.revalidate(); // Remove this line to keep the positions of specialties constant.
    traitPanel.repaint();
  }

  public void setDeleteButtonEnabled(boolean enabled) {
    deleteButton.setEnabled(enabled);
  }
}