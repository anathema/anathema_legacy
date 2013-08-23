package net.sf.anathema.platform.fx;

import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;

import static javafx.scene.input.KeyCodeCombinationBuilder.create;
import static javafx.scene.input.KeyCombination.ModifierValue.ANY;
import static javafx.scene.input.KeyCombination.ModifierValue.DOWN;

public class FxAcceleratorMap implements AcceleratorMap {
  private ObservableMap<KeyCombination, Runnable> accelerators;

  public FxAcceleratorMap(ObservableMap<KeyCombination, Runnable> accelerators) {
    this.accelerators = accelerators;
  }

  @Override
  public void register(Hotkey hotkey, final Command command) {
    KeyCombination combination = createKeyCombination(hotkey);
    accelerators.put(combination, new Runnable() {
      @Override
      public void run() {
        command.execute();
      }
    });
  }

  public static KeyCombination createKeyCombination(Hotkey hotkey) {
    KeyCode character = KeyCode.valueOf(hotkey.asString().toUpperCase());
    return create().shortcut(DOWN).alt(ANY).shift(ANY).control(ANY).meta(ANY).code(character).build();
  }
}