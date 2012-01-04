package net.sf.anathema.character.lunar.reporting.content.equipment;

import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.reporting.content.ArmourContent;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.resources.IResources;

public class LunarArmourContent extends ArmourContent{

  public LunarArmourContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  protected IEquipmentPrintModel getEquipmentModel() {
    IBeastformModel model = (IBeastformModel) getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    return model.getEquipmentModel();
  }
}
