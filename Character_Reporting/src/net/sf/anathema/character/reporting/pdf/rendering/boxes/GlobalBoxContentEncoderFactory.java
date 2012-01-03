package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;

public abstract class GlobalBoxContentEncoderFactory extends AbstractBoxContentEncoderFactory {

  public GlobalBoxContentEncoderFactory(String id) {
    super(id);
  }

  @Override
  public final boolean supports(BasicContent content) {
    return true;
  }
}
