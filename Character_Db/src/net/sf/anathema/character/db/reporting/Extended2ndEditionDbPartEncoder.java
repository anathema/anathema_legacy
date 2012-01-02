package net.sf.anathema.character.db.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionDbPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Extended2ndEditionDbPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  @Override
  public IBoxContentEncoder getGreatCurseEncoder() {
    return new Db2ndEditionGreatCurseEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
