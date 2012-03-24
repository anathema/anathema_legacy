package net.sf.anathema.character.model.charm.learn;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;

public interface IComboLearnStrategy {

  ICombo[] getCurrentCombos(IComboConfiguration configuration);
}