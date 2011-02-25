package net.sf.anathema.character.generic.impl.caste;

import java.util.Map;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class CasteCollection implements ICasteCollection
{
  private Map<IExaltedEdition, ICasteType[]> editionMap;
  private final ICasteType[] allTypes;
  
  public CasteCollection(Map<IExaltedEdition, ICasteType[]> editionMap)
  {
	  this.editionMap = editionMap;
	  ICasteType[] longest = null;
	  int maxLength = 0;
	  for (ICasteType[] casteSet : editionMap.values())
		  if (casteSet.length > maxLength)
			  longest = casteSet;
	  allTypes = longest;
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

  public ICasteType[] getAllCasteTypes(IExaltedEdition edition)
  {
	  if (editionMap == null)
		  return allTypes;
	  else
		  return editionMap.get(edition);
  }
}