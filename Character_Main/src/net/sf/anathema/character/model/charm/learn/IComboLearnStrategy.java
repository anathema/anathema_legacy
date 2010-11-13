package net.sf.anathema.character.model.charm.learn;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;

public interface IComboLearnStrategy {

  public ICombo[] getCurrentCombos(IComboConfiguration configuration);

  public void finalizeCombo(IComboConfiguration configuration);
}