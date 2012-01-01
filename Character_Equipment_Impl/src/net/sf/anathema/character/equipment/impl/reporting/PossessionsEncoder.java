package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.content.PossessionsContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class PossessionsEncoder extends LineFillingContentBoxEncoder<PossessionsContent> {

  public PossessionsEncoder() {
    super(PossessionsContent.class);
  }
}
