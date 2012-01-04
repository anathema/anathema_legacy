package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.sidereal.reporting.layout.Sidereal1stEditionDetailsPageEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.greatcurse.ParadoxEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionSiderealPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new ParadoxEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(PageConfiguration configuration) {
    return new PageEncoder[] { new Sidereal1stEditionDetailsPageEncoder(getResources(), getFontSize(), configuration) };
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
