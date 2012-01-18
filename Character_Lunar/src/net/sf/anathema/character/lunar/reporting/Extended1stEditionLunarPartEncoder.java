package net.sf.anathema.character.lunar.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.lunar.reporting.layout.LunarBeastform1stEditionPageEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.GreatCurse1stEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  private EncoderRegistry encoderRegistry;
  // Should this field be removed?
  private BaseFont baseFont;

  public Extended1stEditionLunarPartEncoder(EncoderRegistry encoderRegistry, IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont, essenceMax);
    this.encoderRegistry = encoderRegistry;
    this.baseFont = baseFont;
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new GreatCurse1stEditionEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }

  @Override
  public PageEncoder[] getAdditionalPages(PageConfiguration configuration) {
    return new PageEncoder[] { new LunarBeastform1stEditionPageEncoder(encoderRegistry, getResources(), configuration) };
  }
}
