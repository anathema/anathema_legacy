package net.sf.anathema.character.intimacies.reporting;

import net.sf.anathema.character.intimacies.reporting.content.SimpleIntimaciesContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class SimpleIntimaciesEncoder extends LineFillingContentBoxEncoder<SimpleIntimaciesContent> {

  public SimpleIntimaciesEncoder() {
    super(SimpleIntimaciesContent.class);
  }
}
