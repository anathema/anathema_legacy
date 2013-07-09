package net.sf.anathema.hero.equipment.sheet.rendering.arsenal;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.BoxBoundsFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.PreferredHeight;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.WEAPON_HEIGHT_SECOND_EDITION;

public class PreferredWeaponryHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    float boxHeight = WEAPON_HEIGHT_SECOND_EDITION;
    return BoxBoundsFactory.getContentHeight(boxHeight);
  }
}