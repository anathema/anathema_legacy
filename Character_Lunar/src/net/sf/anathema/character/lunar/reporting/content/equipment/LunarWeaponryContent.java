package net.sf.anathema.character.lunar.reporting.content.equipment;

import net.sf.anathema.character.equipment.impl.reporting.content.Weaponry2ndEditionContent;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.resources.IResources;

public class LunarWeaponryContent extends Weaponry2ndEditionContent {

  public LunarWeaponryContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  protected IGenericTraitCollection getTraitCollection() {
    IBeastformModel additionalModel = (IBeastformModel) getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    return additionalModel.getBeastTraitCollection();
  }
}
