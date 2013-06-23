package net.sf.anathema.hero.othertraits.sheet.essence.encoder;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;
import net.sf.anathema.hero.othertraits.sheet.essence.content.RegainEssenceContent;

public class RegainEssencePreferredHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    RegainEssenceContent essenceContent = metrics.getSession().createContent(RegainEssenceContent.class);
    return new RegainEssenceTableEncoder().getTableHeight(essenceContent);
  }
}
