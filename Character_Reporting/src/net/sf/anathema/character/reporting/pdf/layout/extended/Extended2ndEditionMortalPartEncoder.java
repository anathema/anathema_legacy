package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public Extended2ndEditionMortalPartEncoder(IResources resources, BaseFont baseFont) {
    super(resources);
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    return null;
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return null;
  }

  @Override
  public boolean hasMagicPage() {
    return false;
  }
}
