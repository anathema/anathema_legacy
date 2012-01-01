package net.sf.anathema.character.lunar.reporting.rendering;

import net.sf.anathema.character.lunar.reporting.content.GiftContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class GiftEncoder extends LineFillingContentBoxEncoder<GiftContent> {

  public GiftEncoder() {
    super(GiftContent.class);
  }
}
