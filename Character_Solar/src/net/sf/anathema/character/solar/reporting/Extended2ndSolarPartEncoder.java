package net.sf.anathema.character.solar.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.solar.reporting.rendering.AnimaEncoderFactory;
import net.sf.anathema.character.solar.reporting.rendering.VirtueFlawEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndSolarPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder() {
    return new VirtueFlawEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
