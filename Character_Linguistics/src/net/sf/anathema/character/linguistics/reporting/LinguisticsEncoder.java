package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentEncoder;

public class LinguisticsEncoder extends LineFillingContentEncoder<LinguisticsContent> {

  public LinguisticsEncoder() {
    super(LinguisticsContent.class);
  }
}
