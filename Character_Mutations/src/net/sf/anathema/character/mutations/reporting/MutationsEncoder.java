package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingContentEncoder;

public class MutationsEncoder extends LineFillingContentEncoder<MutationContent> {

  public MutationsEncoder() {
    super(MutationContent.class);
  }
}
