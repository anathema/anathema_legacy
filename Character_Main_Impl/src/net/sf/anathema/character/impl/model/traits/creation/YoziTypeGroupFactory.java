package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.lib.util.IIdentificate;

import net.sf.anathema.character.generic.traits.types.YoziType;;

public class YoziTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected IIdentificate getGroupIdentifier(ICasteCollection casteCollection, String groupId) {
	  return YoziType.valueOf(groupId);
  }
}