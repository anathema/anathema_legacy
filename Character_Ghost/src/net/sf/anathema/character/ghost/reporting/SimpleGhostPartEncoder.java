package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SimpleGhostPartEncoder extends AbstractSimplePartEncoder {

  public SimpleGhostPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new GhostFetterEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new GhostPassionEncoder();
  }
}
