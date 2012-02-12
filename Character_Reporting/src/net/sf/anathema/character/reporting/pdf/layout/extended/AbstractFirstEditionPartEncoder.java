package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionPartEncoder implements IExtendedPartEncoder {

  private final IResources resources;

  protected AbstractFirstEditionPartEncoder(IResources resources) {
    this.resources = resources;
  }

  public final IResources getResources() {
    return resources;
  }

  @Override
  public ContentEncoder getHealthEncoder() {
    return null;
  }

  @Override
  public ContentEncoder getMovementEncoder() {
    return null;
  }

  @Override
  public IVariableContentEncoder[] getAdditionalFirstPageEncoders() {
    return new IVariableContentEncoder[0];
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[0];
  }
}
