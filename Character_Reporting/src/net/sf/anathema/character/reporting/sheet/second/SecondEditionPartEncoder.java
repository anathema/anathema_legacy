package net.sf.anathema.character.reporting.sheet.second;

import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.AbstractPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private final PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources, int essenceMax) throws DocumentException, IOException {
    super(resources, essenceMax);
    this.boxEncoder = new PdfBoxEncoder(getBaseFont());
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(getBaseFont(), getResources());
    encoder.encodePersonalInfos(directContent, character, infoBounds);
  }

  public void encodeEditionSpecificFirstPagePart(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop) {
    encodeVirtues(directContent, character, distanceFromTop, 80);
  }

  private void encodeVirtues(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop,
      int virtueHeight) {
    SmartRectangle virtueBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, virtueHeight, 1);
    String virtueHeader = getResources().getString("Sheet.Header.Virtues"); //$NON-NLS-1$
    SmartRectangle virtueContentBounds = boxEncoder.encodeBox(directContent, virtueBounds, virtueHeader);
    new PdfVirtueEncoder(getResources(), getBaseFont()).encodeVirtues(directContent, character, virtueContentBounds);
  }
}