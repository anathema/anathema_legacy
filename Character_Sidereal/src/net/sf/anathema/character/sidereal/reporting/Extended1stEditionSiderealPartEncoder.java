package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Sidereal1stEditionDetailsPageEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.sidereal.reporting.rendering.greatcurse.ParadoxEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new ParadoxEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[]{new Sidereal1stEditionDetailsPageEncoder(getResources(), IVoidStateFormatConstants.SMALLER_FONT_SIZE, configuration)};
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
