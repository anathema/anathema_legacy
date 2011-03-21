package net.sf.anathema.character.generic.impl.caste;

import java.util.Map;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;

public class CasteCollection implements ICasteCollection
{
  private Map<ITemplateType, ICasteType[]> templateMap;
  private Map<IExaltedEdition, ICasteType[]> editionMap;
  private final ICasteType[] allTypes;
  
  public CasteCollection(ICasteType[] allTypes,
		  Map<IExaltedEdition, ICasteType[]> editionMap,
		  Map<ITemplateType, ICasteType[]> templateMap)
  {
	  this.editionMap = editionMap;
	  this.templateMap = templateMap;
	  this.allTypes = allTypes;
  }

  public CasteCollection(ICasteType[] allTypes) {
    this.allTypes = allTypes;
  }

  public ICasteType getById(String casteTypeId) {
    for (ICasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId); //$NON-NLS-1$
  }

  public boolean containsCasteType(String casteTypeId) {
    for (ICasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }

  public boolean isEmpty() {
    return allTypes.length <= 0;
  }

  public ICasteType[] getAllCasteTypes(IExaltedEdition edition, ITemplateType template)
  {
	  ICasteType[] set = null;
	  
	  if (templateMap != null)
		  set = set != null ? set : templateMap.get(template);
	  
	  if (editionMap != null)
		  set = set != null ? set : editionMap.get(edition);
	  
	  set = set != null ? set : allTypes;
	  
	  return set;
  }
}