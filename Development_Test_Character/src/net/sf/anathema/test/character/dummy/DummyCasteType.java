package net.sf.anathema.test.character.dummy;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.lib.util.Identificate;

public class DummyCasteType extends Identificate implements ICasteType<IDummyCasteTypeVisitor> {

  public DummyCasteType() {
    this("DummyCaste"); //$NON-NLS-1$
  }

  public DummyCasteType(String id) {
    super(id);
  }

  public void accept(IDummyCasteTypeVisitor visitor) {
    // Nothing to do
  }
}