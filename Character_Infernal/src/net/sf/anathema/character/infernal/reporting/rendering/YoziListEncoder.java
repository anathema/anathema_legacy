package net.sf.anathema.character.infernal.reporting.rendering;

import net.sf.anathema.character.infernal.reporting.content.InfernalYoziListContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentEncoder;

public class YoziListEncoder extends LineFillingContentEncoder<InfernalYoziListContent> {

  public YoziListEncoder() {
    super(InfernalYoziListContent.class);
  }
}