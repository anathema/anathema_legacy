package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.reporting.layout.LunarBeastform1stEditionPageEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.GreatCurse1stEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionLunarPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new GreatCurse1stEditionEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[]{new LunarBeastform1stEditionPageEncoder(encoderRegistry, getResources(), configuration)};
  }
}
