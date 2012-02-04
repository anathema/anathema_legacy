package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.solar.reporting.rendering.AnimaEncoderFactory;
import net.sf.anathema.character.solar.reporting.rendering.VirtueFlawEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSolarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionSolarPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return new VirtueFlawEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
