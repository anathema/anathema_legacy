package net.sf.anathema.character.impl.model.context.magic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

public class ExperiencedComboLearnStrategy implements IComboLearnStrategy {

  public ICombo[] getCurrentCombos(IComboConfiguration configuration) {
    List<ICombo> list = new ArrayList<ICombo>();
    list.addAll(Arrays.asList(configuration.getCreationCombos()));
    list.addAll(Arrays.asList(configuration.getExperienceLearnedCombos()));
    return list.toArray(new ICombo[list.size()]);
  }

  public void finalizeCombo(IComboConfiguration configuration) {
    configuration.finalizeCombo(true);
  }
}