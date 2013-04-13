package net.sf.anathema.character.impl.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.library.trait.view.AbstractTraitView;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;
import org.jmock.example.announcer.Announcer;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class SpecialtyView extends AbstractTraitView implements ISpecialtyView {
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final Component abilityLabel;
  private final Component separatorLabel = new JLabel("-");
  private final Component specialtyLabel;
  private JButton deleteButton;
  private JPanel traitPanel;
  private final Icon deleteIcon;

  public SpecialtyView(IntegerViewFactory configuration, String labelText, Icon deleteIcon, String id, int value, int maxValue) {
    super(configuration, labelText, value, maxValue, null);
    this.deleteIcon = deleteIcon;
    specialtyLabel = new JLabel(id);
    abilityLabel = new JLabel(labelText);
  }

  @SuppressWarnings("serial")
  public void addComponents(JPanel panel) {
    this.traitPanel = panel;
    panel.add(abilityLabel);
    panel.add(separatorLabel);
    panel.add(specialtyLabel, new CC().pushX().growX());
    panel.add(getValueDisplay().getComponent());
    deleteButton = new JButton(new AbstractAction(null, deleteIcon) {
      @Override
      public void actionPerformed(ActionEvent e) {
        fireDeletionPerformed();
      }
    });
    deleteButton.setPreferredSize(new Dimension(deleteIcon.getIconWidth() + 4, deleteIcon.getIconHeight() + 4));
    panel.add(deleteButton, SwingLayoutUtils.constraintsForImageButton(deleteButton));
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
    traitPanel.remove(abilityLabel);
    traitPanel.remove(separatorLabel);
    traitPanel.remove(specialtyLabel);
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