package net.sf.anathema.character.equipment.reporting.rendering.possessions;

import net.sf.anathema.character.equipment.reporting.content.PossessionsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class PossessionsEncoder extends LineFillingContentEncoder<PossessionsContent> {

  public PossessionsEncoder() {
    super(PossessionsContent.class);
  }
}
