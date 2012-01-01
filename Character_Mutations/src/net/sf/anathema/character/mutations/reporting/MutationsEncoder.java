package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentBoxEncoder;

public class MutationsEncoder extends LineFillingContentBoxEncoder<MutationContent> {

  public MutationsEncoder() {
    super(MutationContent.class);
  }
}
