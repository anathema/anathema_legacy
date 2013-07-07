package net.sf.anathema.hero.charms.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.charms.sheet.content.GenericCharmContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.PreferredHeight;
import net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants;

public class PreferredGenericCharmHeight implements PreferredHeight {

  private static final float PADDING = 5.794f;

  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    GenericCharmContent content = createContent(metrics.getSession());
    if (!content.hasContent()) {
      return 0;
    }
    return getPreferredContentHeight(metrics);
  }

  private float getPreferredContentHeight(EncodingMetrics metrics) {
    GenericCharmContent content = createContent(metrics.getSession());
    float traitHeight = 0;
    for (String traitLabel : content.getTraitLabels()) {
      float height = metrics.getTextMetrics().getTableTextWidth(traitLabel) + PADDING;
      if (height > traitHeight) {
        traitHeight = height;
      }
    }
    return traitHeight + content.getGenericCharmCount() * IVoidStateFormatConstants.LINE_HEIGHT;
  }

  private GenericCharmContent createContent(ReportSession session) {
    return session.createContent(GenericCharmContent.class);
  }
}
