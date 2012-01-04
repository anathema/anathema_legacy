package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionPartEncoder implements IExtendedPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  protected AbstractFirstEditionPartEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public final IResources getResources() {
    return resources;
  }

  public final BaseFont getBaseFont() {
    return baseFont;
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
  public PageEncoder[] getAdditionalPages(PageConfiguration configuration) {
    return new PageEncoder[0];
  }
}
