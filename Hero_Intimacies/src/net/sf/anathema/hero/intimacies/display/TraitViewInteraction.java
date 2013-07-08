package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.icon.EmptyIcon;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraitViewInteraction implements ToggleTool {

  private final CommandProxy commandProxy = new CommandProxy();
  private final JToggleButton button;

  public TraitViewInteraction() {
    this.button = new JToggleButton();
    button.setPreferredSize(new Dimension(20, 20));
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        commandProxy.execute();
      }
    });
    resetIcons();
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    button.setIcon(loadIcon(relativePath));
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    throw new UnsupportedOperationException("We'll probably never need this.");
  }

  @Override
  public void setTooltip(String key) {
    button.setToolTipText(key);
  }

  @Override
  public void setText(String key) {
    throw new UnsupportedOperationException("No text in traitviews");
  }

  @Override
  public void enable() {
    resetIcons();
    button.setEnabled(true);
  }

  @Override
  public void disable() {
    resetIcons();
    button.setEnabled(false);
  }

  @Override
  public void setCommand(Command command) {
    commandProxy.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey s) {
    throw new NotYetImplementedException();
  }

  @Override
  public void select() {
    resetIcons();
    button.setSelected(true);
  }

  @Override
  public void deselect() {
    resetIcons();
    button.setSelected(false);
  }

  public JComponent getButton() {
    return button;
  }

  private void resetIcons() {
    //button.setIconSet(loadIcon(properties.createStandardIcon()), loadIcon(properties.createUnselectedIcon()));
  }

  private Icon loadIcon(RelativePath path) {
    if (path == AgnosticUIConfiguration.NO_ICON) {
      return new EmptyIcon(new Dimension(16, 16));
    }
    return new ImageProvider().getImageIcon(path);
  }
}