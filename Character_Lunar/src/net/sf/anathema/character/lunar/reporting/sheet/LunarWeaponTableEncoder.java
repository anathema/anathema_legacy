package net.sf.anathema.character.lunar.reporting.sheet;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.SecondEditionWeaponryTableEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.lib.resources.IResources;

public class LunarWeaponTableEncoder extends SecondEditionWeaponryTableEncoder {
  IGenericCharacter character;

  public LunarWeaponTableEncoder(BaseFont baseFont, IResources resources, IGenericCharacter character) {
    super(baseFont, resources, character.getEquipmentModifiers());
    this.character = character;
  }

  @Override
  protected IGenericTraitCollection getTraitCollection(IGenericCharacter character) {
    return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
  }
}
