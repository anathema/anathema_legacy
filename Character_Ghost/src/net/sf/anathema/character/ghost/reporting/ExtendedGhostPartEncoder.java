package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedGhostPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public ExtendedGhostPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new GhostFetterEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new GhostPassionEncoder(getBaseFont(), getResources());
  }
}
