package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.value.IconToggleButton;
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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraitViewInteraction implements ToggleTool {

  private final CommandProxy commandProxy = new CommandProxy();
  private final IconToggleButton button;
  private final IIconToggleButtonProperties properties;

  public TraitViewInteraction(IIconToggleButtonProperties properties) {
    this.properties = properties;
    this.button = new IconToggleButton(new EmptyIcon(new Dimension(16, 16)));
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
    throw new UnsupportedOperationException("currently manages icon via properties");
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
    return button.getComponent();
  }

  private void resetIcons() {
    button.setIconSet(loadIcon(properties.createStandardIcon()), loadIcon(properties.createUnselectedIcon()));
  }

  private Icon loadIcon(RelativePath path) {
    if (path == AgnosticUIConfiguration.NO_ICON) {
      return new EmptyIcon(new Dimension(16, 16));
    }
    return new ImageProvider().getImageIcon(path);
  }
}