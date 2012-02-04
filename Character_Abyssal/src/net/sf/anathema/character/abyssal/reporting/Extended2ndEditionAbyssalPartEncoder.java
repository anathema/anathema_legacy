package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.abyssal.reporting.rendering.Anima2ndEditionEncoderFactory;
import net.sf.anathema.character.abyssal.reporting.rendering.Resonance2ndEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionAbyssalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionAbyssalPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return new Resonance2ndEditionEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new Anima2ndEditionEncoderFactory().create(getResources(), content);
  }
}
