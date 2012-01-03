package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;

public abstract class GlobalEncoderFactory extends AbstractEncoderFactory {

  public GlobalEncoderFactory(String id) {
    super(id);
  }

  @Override
  public final boolean supports(BasicContent content) {
    return true;
  }
}
