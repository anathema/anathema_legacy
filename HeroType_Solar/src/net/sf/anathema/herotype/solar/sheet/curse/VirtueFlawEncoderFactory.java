package net.sf.anathema.herotype.solar.sheet.curse;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class VirtueFlawEncoderFactory extends AbstractEncoderFactory {

  public VirtueFlawEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new VirtueFlawEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}
