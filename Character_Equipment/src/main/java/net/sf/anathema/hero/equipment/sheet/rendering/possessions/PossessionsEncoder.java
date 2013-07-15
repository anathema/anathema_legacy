package net.sf.anathema.hero.equipment.sheet.rendering.possessions;

import net.sf.anathema.hero.equipment.sheet.content.PossessionsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class PossessionsEncoder extends LineFillingContentEncoder<PossessionsContent> {

  public PossessionsEncoder() {
    super(PossessionsContent.class);
  }
}
