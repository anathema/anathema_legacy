package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Sidereal2ndEditionDetailsPageEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.sidereal.reporting.rendering.greatcurse.FlawedFateEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return new FlawedFateEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[]{new Sidereal2ndEditionDetailsPageEncoder(getResources(), getFontSize(), configuration)};
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
