package net.sf.anathema.character.lunar.reporting.rendering;

import net.sf.anathema.character.lunar.reporting.content.GiftContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentEncoder;

public class GiftEncoder extends LineFillingContentEncoder<GiftContent> {

  public GiftEncoder() {
    super(GiftContent.class);
  }
}
