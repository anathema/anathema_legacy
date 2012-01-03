package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSimplePartEncoder implements ISimplePartEncoder {

  private final IResources resources;

  protected AbstractSimplePartEncoder(IResources resources) {
    this.resources = resources;
  }

  public final IResources getResources() {
    return resources;
  }
}
