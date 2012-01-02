package net.sf.anathema.character.spirit.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionSpiritPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Simple2ndEditionSpiritPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  // TODO: This should be properly edited out, not just nulled out.
  public IBoxContentEncoder getGreatCurseEncoder() {
    return new NullBoxContentEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SpiritAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
