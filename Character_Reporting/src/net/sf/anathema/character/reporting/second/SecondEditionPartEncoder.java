package net.sf.anathema.character.reporting.second;

import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.encoder.AbstractPdfPartEncoder;
import net.sf.anathema.character.reporting.encoder.PdfBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources, int essenceMax) throws DocumentException, IOException {
    super(resources, essenceMax);
    this.boxEncoder = new PdfBoxEncoder(getBaseFont());
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(getBaseFont(), getResources());
    encoder.encodePersonalInfos(directContent, character, infoBounds);
  }

  public void encodeEditionSpecificFirstPagePart(PdfContentByte directContent, SmartRectangle restBounds) {
    boxEncoder.encodeBox(directContent, restBounds, "Rest");
  }
}