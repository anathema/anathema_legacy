package net.sf.anathema.character.intimacies.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedIntimaciesEncoderFactory extends AbstractBoxContentEncoderFactory {

  public ExtendedIntimaciesEncoderFactory() {
    super(EncoderIds.INTIMACIES_EXTENDED);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedIntimaciesEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}
