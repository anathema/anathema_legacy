package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeValue;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.WEAPON_HEIGHT_FIRST_EDITION;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.WEAPON_HEIGHT_SECOND_EDITION;

public class PreferredWeaponryHeight implements EncoderAttributeValue {
  @Override
  public float getValue(BasicContent content) {
    return content.isFirstEdition() ? WEAPON_HEIGHT_FIRST_EDITION : WEAPON_HEIGHT_SECOND_EDITION;
  }
}
