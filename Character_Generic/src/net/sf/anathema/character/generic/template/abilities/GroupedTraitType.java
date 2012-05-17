package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;

import java.util.List;

public class GroupedTraitType implements IGroupedTraitType {

  private final ITraitType type;
  private final String groupId;
  private final String groupCasteId;
  private List<String> traitCasteIds;

  public GroupedTraitType(ITraitType type, Identified identificate, String casteId) {
    this(type, identificate.getId(), casteId);
  }

  public GroupedTraitType(ITraitType type, String groupId, String casteId)
  {
	  this.type = type;
	  this.groupId = groupId;
	  this.groupCasteId = casteId;
  }
  
  public GroupedTraitType(ITraitType type, List<String> traitCastes, String groupId)
  {
    this(type, groupId, null);
    this.traitCasteIds = traitCastes;
  }

  @Override
  public ITraitType getTraitType() {
    return type;
  }

  @Override
  public String getGroupId() {
    return groupId;
  }

  @Override
  public String getGroupCasteId() {
    return groupCasteId;
  }
  
  @Override
  public List<String> getTraitCasteSet()
  {
	  return traitCasteIds; 
  }
}