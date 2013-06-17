package net.sf.anathema.hero.attributes.template;

import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.util.Identifier;

public class TraitLearnValues {

  public final Identifier id;
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;

  public TraitLearnValues(Identifier id, int startingValue) {
    this.id = id;
    this.creationValue = startingValue;
  }
}
