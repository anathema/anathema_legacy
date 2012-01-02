package net.sf.anathema.character.db.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionDbPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Simple2ndEditionDbPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
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
