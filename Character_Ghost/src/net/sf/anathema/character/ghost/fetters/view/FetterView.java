package net.sf.anathema.character.ghost.fetters.view;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.trait.view.AbstractTraitView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class FetterView extends AbstractTraitView implements IFetterView {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final Component fetterLabel;
  private JButton deleteButton;
  private JPanel traitPanel;
  private final Icon deleteIcon;

  public FetterView(
      IntegerViewFactory configuration,
      Icon deleteIcon,
      String id,
      int value,
      int maxValue) {
    super(configuration, "", value, maxValue, null);
    this.deleteIcon = deleteIcon;
    fetterLabel = new JLabel(id);
  }

  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    panel.add(fetterLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(getValueDisplay().getComponent());
    deleteButton = new JButton(new AbstractAction(null, deleteIcon) {

		@Override
        public void actionPerformed(ActionEvent e) {
	        fireDeletionPerformed();
	      }
    });
    deleteButton.setPreferredSize(new Dimension(deleteIcon.getIconWidth() + 4, deleteIcon.getIconHeight() + 4));
    panel.add(deleteButton);
    panel.revalidate();
  }

  @Override
  public void addDeleteListener(IChangeListener listener) {
    control.addListener(listener);
  }

  private void fireDeletionPerformed() {
    control.announce().changeOccurred();
  }

  @Override
  public void delete() {
    traitPanel.remove(fetterLabel);
    traitPanel.remove(getValueDisplay().getComponent());
    traitPanel.remove(deleteButton);
    traitPanel.revalidate(); // Remove this line to keep the positions of specialties constant.
    traitPanel.repaint();
  }

  @Override
  public void setDeleteButtonEnabled(boolean enabled) {
    deleteButton.setEnabled(enabled);
  }
}