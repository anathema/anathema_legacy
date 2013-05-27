package net.sf.anathema.platform.fx;

import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.platform.Hotkey;

import static javafx.scene.input.KeyCharacterCombinationBuilder.create;
import static javafx.scene.input.KeyCombination.ModifierValue.ANY;
import static javafx.scene.input.KeyCombination.ModifierValue.DOWN;

public class FxAcceleratorMap implements AcceleratorMap {
  private ObservableMap<KeyCombination, Runnable> accelerators;

  public FxAcceleratorMap(ObservableMap<KeyCombination, Runnable> accelerators) {
    this.accelerators = accelerators;
  }

  @Override
  public void register(Hotkey hotkey, final Command command) {
    KeyCharacterCombination combination =
            create().shortcut(ANY).alt(DOWN).shift(ANY).control(ANY).meta(ANY).character("s").build();
    accelerators.put(combination, new Runnable() {
      @Override
      public void run() {
        command.execute();
      }
    });
  }
}