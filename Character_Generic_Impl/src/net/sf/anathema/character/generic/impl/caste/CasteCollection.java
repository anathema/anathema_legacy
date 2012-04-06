package net.sf.anathema.character.generic.impl.caste;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITemplateType;

import java.util.Map;

public class CasteCollection implements ICasteCollection {
  private Map<ITemplateType, ICasteType[]> templateMap;
  private final ICasteType[] allTypes;

  public CasteCollection(ICasteType[] allTypes, Map<ITemplateType, ICasteType[]> templateMap) {
    this.templateMap = templateMap;
    this.allTypes = allTypes;
  }

  public CasteCollection(ICasteType[] allTypes) {
    this.allTypes = allTypes;
  }

  @Override
  public ICasteType getById(String casteTypeId) {
    for (ICasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId); //$NON-NLS-1$
  }

  @Override
  public boolean containsCasteType(String casteTypeId) {
    for (ICasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return allTypes.length <= 0;
  }

  @Override
  public ICasteType[] getAllCasteTypes(ITemplateType template) {
    ICasteType[] set = null;
    if (templateMap != null) set = set != null ? set : templateMap.get(template);
    set = set != null ? set : allTypes;
    return set;
  }
}
