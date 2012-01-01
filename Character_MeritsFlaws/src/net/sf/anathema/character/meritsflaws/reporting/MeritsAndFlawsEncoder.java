package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class MeritsAndFlawsEncoder extends LineFillingContentBoxEncoder<MeritsAndFlawsContent> {

  public MeritsAndFlawsEncoder() {
    super(MeritsAndFlawsContent.class);
  }
}
