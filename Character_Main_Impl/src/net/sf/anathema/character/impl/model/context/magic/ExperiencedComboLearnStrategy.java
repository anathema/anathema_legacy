package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperiencedComboLearnStrategy implements IComboLearnStrategy {

  @Override
  public ICombo[] getCurrentCombos(IComboConfiguration configuration) {
    List<ICombo> list = new ArrayList<ICombo>();
    list.addAll(Arrays.asList(configuration.getCreationCombos()));
    list.addAll(Arrays.asList(configuration.getExperienceLearnedCombos()));
    return list.toArray(new ICombo[list.size()]);
  }

  @Override
  public void finalizeCombo(IComboConfiguration configuration) {
    configuration.finalizeCombo(true);
  }
}