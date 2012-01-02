package net.sf.anathema.character.infernal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public ExtendedInfernalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }

  public IVariableBoxContentEncoder[] getAdditionalFirstPageEncoders() {
    return new IVariableBoxContentEncoder[] { new InfernalYoziListEncoder() };
  }
}
