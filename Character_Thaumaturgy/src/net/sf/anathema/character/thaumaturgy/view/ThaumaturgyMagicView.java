package net.sf.anathema.character.thaumaturgy.view;

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
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ThaumaturgyMagicView extends AbstractTraitView implements IThaumaturgyMagicView {

  private final ChangeControl control = new ChangeControl();
  private final Component artLabel;
  private final Component separatorLabel = new JLabel("-"); //$NON-NLS-1$
  private final Component dummyLabel1 = new JLabel("");
  private final Component dummyLabel2 = new JLabel("");
  private final Component procedureLabel;
  private JButton deleteButton;
  private JPanel traitPanel;
  private final Icon deleteIcon;

  public ThaumaturgyMagicView(
      IIntValueDisplayFactory configuration,
      String labelText,
      Icon deleteIcon,
      String id,
      int value,
      int maxValue) {
    super(configuration, labelText, value, maxValue, null);
    this.deleteIcon = deleteIcon;
    procedureLabel = id == null ? null : new JLabel(id);
    artLabel = new JLabel(labelText);
  }

  @SuppressWarnings("serial")
  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    panel.add(artLabel);
    if (procedureLabel != null) {
    	panel.add(separatorLabel, GridDialogLayoutData.CENTER);
    	panel.add(procedureLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    }
    else {
    	panel.add(dummyLabel1, GridDialogLayoutData.CENTER);
    	panel.add(dummyLabel2, GridDialogLayoutData.FILL_HORIZONTAL);
    }
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
    traitPanel.remove(artLabel);
	if (procedureLabel != null) {
	  traitPanel.remove(separatorLabel);
	  traitPanel.remove(procedureLabel);
	}
	else {
      traitPanel.remove(dummyLabel1);
      traitPanel.remove(dummyLabel2);
	}
    traitPanel.remove(getValueDisplay().getComponent());
    traitPanel.remove(deleteButton);
    traitPanel.revalidate();
    traitPanel.repaint();
  }

  public void setDeleteButtonEnabled(boolean enabled) {
    deleteButton.setEnabled(enabled);
  }
}