package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class MeritsAndFlawsEncoderFactory extends AbstractEncoderFactory {

  public MeritsAndFlawsEncoderFactory() {
    super(net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MERITS_AND_FLAWS);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new MeritsAndFlawsEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition();
  }
}
