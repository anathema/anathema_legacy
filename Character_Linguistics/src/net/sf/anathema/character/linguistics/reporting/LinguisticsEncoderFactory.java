package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class LinguisticsEncoderFactory extends GlobalEncoderFactory {

  public LinguisticsEncoderFactory() {
    super(EncoderIds.LANGUAGES);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new LinguisticsEncoder();
  }
}
