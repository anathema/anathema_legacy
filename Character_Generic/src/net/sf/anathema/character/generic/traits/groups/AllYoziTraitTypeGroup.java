package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.types.YoziType;

public class AllYoziTraitTypeGroup extends TraitTypeGroup {

  static AllYoziTraitTypeGroup instance = new AllYoziTraitTypeGroup();

  public static AllYoziTraitTypeGroup getInstance() {
    return instance;
  }

  private AllYoziTraitTypeGroup() {
    super(YoziType.values());
  }
}