package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class MeritsAndFlawsEncoderFactory extends AbstractBoxContentEncoderFactory {

  public MeritsAndFlawsEncoderFactory() {
    super(net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MERITS_AND_FLAWS);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new MeritsAndFlawsEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition();
  }
}
