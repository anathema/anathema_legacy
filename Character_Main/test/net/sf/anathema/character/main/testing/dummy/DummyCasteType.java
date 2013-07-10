package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class DummyCasteType extends SimpleIdentifier implements CasteType {

  public DummyCasteType() {
    this("DummyCaste");
  }

  public DummyCasteType(String id) {
    super(id);
  }
}