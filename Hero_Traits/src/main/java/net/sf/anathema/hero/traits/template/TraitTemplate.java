package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.traits.model.trait.ModificationType;

public class TraitTemplate {

  public int startValue = 0;
  public int minimumValue = 0;
  public ModificationType modificationType = ModificationType.RaiseOnly;
  public LimitationTemplate limitation = new LimitationTemplate();
}
