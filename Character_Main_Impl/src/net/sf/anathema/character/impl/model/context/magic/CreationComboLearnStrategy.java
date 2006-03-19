package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

public class CreationComboLearnStrategy implements IComboLearnStrategy {

  public ICombo[] getCurrentCombos(IComboConfiguration configuration) {
    return configuration.getCreationCombos();
  }

  public void finalizeCombo(IComboConfiguration configuration) {
    configuration.finalizeCombo(false);
  }
}