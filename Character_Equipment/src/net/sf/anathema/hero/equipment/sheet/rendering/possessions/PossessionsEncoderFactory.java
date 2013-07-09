package net.sf.anathema.hero.equipment.sheet.rendering.possessions;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class PossessionsEncoderFactory extends GlobalEncoderFactory {

  public PossessionsEncoderFactory() {
    super(EncoderIds.POSSESSIONS);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new PossessionsEncoder();
  }
}
