package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.reporting.ArmourTableEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class LunarArmourTableEncoder extends ArmourTableEncoder {

  public LunarArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont, resources);
  }

  @Override
  protected IEquipmentPrintModel getEquipmentModel(IGenericCharacter character) {
    return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getEquipmentModel();
  }
}
