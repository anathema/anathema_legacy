package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxBoundsFactory;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.WEAPON_HEIGHT_FIRST_EDITION;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.WEAPON_HEIGHT_SECOND_EDITION;

public class PreferredWeaponryHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    BasicContent content = metrics.getContent().createSubContent(BasicContent.class);
    float boxHeight = content.isFirstEdition() ? WEAPON_HEIGHT_FIRST_EDITION : WEAPON_HEIGHT_SECOND_EDITION;
    return BoxBoundsFactory.getContentHeight(boxHeight);
  }
}
