package net.sf.anathema.character.generic.impl.template.experience;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class ComboCostCalculator {

  public int getComboCosts(ICharm[] comboCharms) {
    int costs = 0;
    for (ICharm charm : comboCharms) {
      for (IGenericTrait prerequiste : charm.getPrerequisites()) {
        costs += prerequiste.getCurrentValue();
      }
    }
    return costs;
  }
}