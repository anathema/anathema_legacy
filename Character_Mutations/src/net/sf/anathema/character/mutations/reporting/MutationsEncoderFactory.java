package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class MutationsEncoderFactory extends AbstractEncoderFactory {

  public MutationsEncoderFactory() {
    super(EncoderIds.MUTATIONS);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new MutationsEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
