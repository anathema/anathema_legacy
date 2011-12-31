package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryTableEncoder;

public class LunarWeaponTableEncoder {

  public static WeaponryTableEncoder Create(BaseFont baseFont) {
    return new WeaponryTableEncoder(LunarWeaponryContent.class, baseFont);
  }
}
