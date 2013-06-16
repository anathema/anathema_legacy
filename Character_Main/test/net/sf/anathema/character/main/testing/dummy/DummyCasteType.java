package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class DummyCasteType extends SimpleIdentifier implements ICasteType {

  public DummyCasteType() {
    this("DummyCaste");
  }

  public DummyCasteType(String id) {
    super(id);
  }
}