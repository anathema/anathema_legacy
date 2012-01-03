package net.sf.anathema.character.intimacies.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SimpleIntimaciesEncoderFactory extends AbstractEncoderFactory {

  public SimpleIntimaciesEncoderFactory() {
    super(EncoderIds.INTIMACIES_SIMPLE);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new SimpleIntimaciesEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}
