package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry.PreferredWeaponryHeight;
import net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry.WeaponryEncoder;
import net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry.WeaponryTableEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarEquipmentEncoders;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class LunarWeaponryEncoderFactory extends AbstractBoxContentEncoderFactory {

  public static final String ID = LunarWeaponryEncoderFactory.class.getName();

  public LunarWeaponryEncoderFactory() {
    super(ID);
    setAttribute(EncoderAttributeType.PreferredHeight, new PreferredWeaponryHeight());
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    WeaponryTableEncoder weaponTableEncoder = LunarEquipmentEncoders.CreateWeaponryEncoder();
    return new WeaponryEncoder(weaponTableEncoder);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }

}
