package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;

public abstract class GlobalEncoderFactory extends AbstractEncoderFactory {

  public GlobalEncoderFactory(String id) {
    super(id);
  }

  @Override
  public final boolean supports(BasicContent content) {
    return true;
  }
}
