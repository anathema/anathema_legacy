package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.resources.Resources;

public class TraitTypeInternationalizer {

  private final Resources resources;

  public TraitTypeInternationalizer(Resources resources) {
    this.resources = resources;
  }

  public String getScreenName(TraitType type) {
    return resources.getString(type.getId());
  }
}