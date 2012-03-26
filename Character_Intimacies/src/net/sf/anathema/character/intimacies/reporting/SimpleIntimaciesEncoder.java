package net.sf.anathema.character.intimacies.reporting;

import net.sf.anathema.character.intimacies.reporting.content.SimpleIntimaciesContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentEncoder;

public class SimpleIntimaciesEncoder extends LineFillingContentEncoder<SimpleIntimaciesContent> {

  public SimpleIntimaciesEncoder() {
    super(SimpleIntimaciesContent.class);
  }
}
