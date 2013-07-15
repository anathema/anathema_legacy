package net.sf.anathema.hero.concept;

import net.sf.anathema.lib.util.Identifier;

public interface CasteType extends Identifier {

  CasteType NULL_CASTE_TYPE = new CasteType() {
    @Override
    public String getId() {
      return null;
    }
  };
}