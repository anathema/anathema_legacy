package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.ArmourTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.content.ArmourContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;

public class LunarEquipmentEncoders {

  public static WeaponryTableEncoder CreateWeaponryEncoder(BaseFont baseFont) {
    return new WeaponryTableEncoder(LunarWeaponryContent.class, baseFont);
  }
  
  public static ArmourTableEncoder CreateArmourEncoder(BaseFont baseFont) {
    return new ArmourTableEncoder(LunarArmourContent.class, baseFont);
  }
}
