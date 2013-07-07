package net.sf.anathema.character.main.caste;

import com.google.common.base.Functions;
import net.sf.anathema.character.main.template.ITemplateType;

import java.util.HashMap;
import java.util.Map;

public class CasteCollection implements ICasteCollection {
  private final Map<ITemplateType, CasteType[]> templateMap = new HashMap<>();
  private final CasteType[] allTypes;

  public CasteCollection(CasteType[] allTypes, Map<ITemplateType, CasteType[]> templateMap) {
    this(allTypes);
    this.templateMap.putAll(templateMap);
  }

  public CasteCollection(CasteType[] allTypes) {
    this.allTypes = allTypes;
  }

  @Override
  public CasteType getById(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId);
  }

  @Override
  public boolean isEmpty() {
    return allTypes.length <= 0;
  }

  @Override
  public boolean containsCasteType(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public CasteType[] getAllCasteTypes(ITemplateType template) {
    return Functions.forMap(templateMap, allTypes).apply(template);
  }
}