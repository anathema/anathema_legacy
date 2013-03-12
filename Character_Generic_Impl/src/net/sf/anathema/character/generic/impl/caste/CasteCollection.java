package net.sf.anathema.character.generic.impl.caste;

import com.google.common.base.Functions;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITemplateType;

import java.util.HashMap;
import java.util.Map;

public class CasteCollection implements ICasteCollection {
  private final Map<ITemplateType, ICasteType[]> templateMap = new HashMap<>();
  private final ICasteType[] allTypes;

  public CasteCollection(ICasteType[] allTypes, Map<ITemplateType, ICasteType[]> templateMap) {
    this(allTypes);
    this.templateMap.putAll(templateMap);
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
  public boolean isEmpty() {
    return allTypes.length <= 0;
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
  public ICasteType[] getAllCasteTypes(ITemplateType template) {
    return Functions.forMap(templateMap, allTypes).apply(template);
  }
}