package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.util.Identified;

public class TraitLearnValues {

  public final Identified id;
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;

  public TraitLearnValues(Identified id, int startingValue) {
    this.id = id;
    this.creationValue = startingValue;
  }
}
