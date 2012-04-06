package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import net.sf.anathema.character.reporting.pdf.content.essence.RegainEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;

public class RegainEssencePreferredHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    RegainEssenceContent essenceContent = metrics.getSession().createContent(RegainEssenceContent.class);
    return new RegainEssenceTableEncoder().getTableHeight(essenceContent);
  }
}
