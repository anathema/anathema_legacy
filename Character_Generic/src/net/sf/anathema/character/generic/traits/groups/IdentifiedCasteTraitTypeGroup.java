package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentifiedCasteTraitTypeGroup extends IdentifiedTraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final ICasteType< ? extends ICasteTypeVisitor> casteType;

  public IdentifiedCasteTraitTypeGroup(
      ITraitType[] traitTypes,
      IIdentificate groupId,
      ICasteType< ? extends ICasteTypeVisitor> casteType) {
    super(traitTypes, groupId);
    this.casteType = casteType;
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCasteType() {
    return casteType;
  }
}