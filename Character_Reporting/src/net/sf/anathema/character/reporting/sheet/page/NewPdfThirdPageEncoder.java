package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.common.willpower.NewPdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfThirdPageEncoder extends AbstractPdfPageEncoder {

  public NewPdfThirdPageEncoder(IPdfPartEncoder partEncoder,
                                 PdfEncodingRegistry registry,
                                 IResources resources, int essenceMax,
                                 PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
  }

  public void encode(Document document, PdfContentByte directContent,
                     IGenericCharacter character,
                     IGenericDescription description) throws DocumentException {
    // Essence box
    int distanceFromTop = 0;
    float essenceHeight = encodeEssence(directContent, character, description, distanceFromTop, CONTENT_HEIGHT);
    float willpowerHeight = encodeWillpower(directContent, character, description, distanceFromTop, 96.625f);
    
    distanceFromTop += calculateBoxIncrement(Math.max(essenceHeight, willpowerHeight));

    encodeCopyright(directContent);
  }

  private float encodeEssence(PdfContentByte directContent,
                              IGenericCharacter character,
                              IGenericDescription description,
                              float distanceFromTop, float height)
      throws DocumentException {
    // TODO: Eliminate unchecked casting
    return encodeVariableBox(directContent, character, description,
                             (IPdfVariableContentBoxEncoder) getPartEncoder().getEssenceEncoder(),
                             2, 2, distanceFromTop, height);
  }

  private float encodeWillpower(PdfContentByte directContent,
                                IGenericCharacter character,
                                IGenericDescription description,
                                float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          new NewPdfWillpowerEncoder(getResources(), getBaseFont(), getBaseFont()),
                          1, 1, distanceFromTop, height);
  }
  
  public boolean hasContent(IGenericCharacter character,
                            IGenericDescription description) {
    return false;
  }
}