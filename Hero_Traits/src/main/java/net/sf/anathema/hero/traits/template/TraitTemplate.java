package net.sf.anathema.hero.traits.template;

import net.sf.anathema.character.main.traits.ModificationType;

public class TraitTemplate {

  public int startValue = 0;
  public int minimumValue = 0;
  public ModificationType modificationType = ModificationType.RaiseOnly;
  public LimitationTemplate limitation = new LimitationTemplate();
}
