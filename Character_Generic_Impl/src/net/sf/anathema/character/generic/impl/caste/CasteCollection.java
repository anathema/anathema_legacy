package net.sf.anathema.character.generic.impl.caste;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public class CasteCollection implements ICasteCollection {

  private final ICasteType[] allTypes;

  public CasteCollection(ICasteType[] allTypes) {
    this.allTypes = allTypes;
  }

  public ICasteType< ? extends ICasteTypeVisitor> getById(String casteTypeId) {
    for (ICasteType< ? extends ICasteTypeVisitor> type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId); //$NON-NLS-1$
  }

  public boolean containsCasteType(String casteTypeId) {
    for (ICasteType< ? extends ICasteTypeVisitor> type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }

  public boolean isEmpty() {
    return allTypes.length <= 0;
  }

  public ICasteType[] getAllCasteTypes() {
    return allTypes;
  }
}