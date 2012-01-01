package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SimpleGhostPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SimpleGhostPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new GhostFetterEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new GhostPassionEncoder();
  }
}
