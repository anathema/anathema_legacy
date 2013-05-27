package net.sf.anathema.character.library.removableentry.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.CommandAction;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

public class RemovableStringView implements IRemovableEntryView {

  private final JButton button;
  private final JLabel label;
  private JPanel contentPanel;

  public RemovableStringView(Icon removeIcon, String string) {
    this.label = new JLabel(string);
    this.button = new JButton(removeIcon);
    button.setPreferredSize(new Dimension(removeIcon.getIconWidth() + 4, removeIcon.getIconHeight() + 4));
  }

  public void addContent(JPanel panel) {
    this.contentPanel = panel;
    panel.add(label, new CC().growX().pushX().alignY("top"));
    panel.add(button, SwingLayoutUtils.constraintsForImageButton(button).alignY("top"));
    panel.revalidate();
  }

  @Override
  public void addButtonListener(final Command command) {
    button.setAction(new CommandAction(command));
  }

  @Override
  public void setButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  @Override
  public void delete() {
    contentPanel.remove(label);
    contentPanel.remove(button);
    contentPanel.revalidate();
  }
}