package net.sf.anathema.hero.intimacies.sheet.encoder;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class SimpleIntimaciesEncoderFactory extends AbstractEncoderFactory {

  public SimpleIntimaciesEncoderFactory() {
    super(EncoderIds.INTIMACIES_SIMPLE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SimpleIntimaciesEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
