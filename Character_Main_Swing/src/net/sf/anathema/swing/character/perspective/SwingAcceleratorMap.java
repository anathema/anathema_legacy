package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.lib.gui.CommandAction;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class SwingAcceleratorMap implements AcceleratorMap {
  private InputMap inputMap;
  private ActionMap actionMap;

  public SwingAcceleratorMap(ActionMap actionMap, InputMap inputMap) {
    this.actionMap = actionMap;
    this.inputMap = inputMap;
  }

  @Override
  public void register(Hotkey hotkey, Command command) {
    Object actionMapKey = new Object();
    inputMap.put(KeyStroke.getKeyStroke(hotkey.asCharacter(), InputEvent.ALT_MASK), actionMapKey);
    actionMap.put(actionMapKey, new CommandAction(command));
  }
}
