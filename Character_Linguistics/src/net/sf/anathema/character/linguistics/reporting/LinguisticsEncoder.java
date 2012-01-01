package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class LinguisticsEncoder extends LineFillingContentBoxEncoder<LinguisticsContent> {

  public LinguisticsEncoder() {
    super(LinguisticsContent.class);
  }
}
