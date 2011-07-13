package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfThirdPageEncoder extends AbstractPdfPageEncoder {
  private final int essenceMax;

  public NewPdfThirdPageEncoder(IPdfPartEncoder partEncoder,
                                 PdfEncodingRegistry registry,
                                 IResources resources, int essenceMax,
                                 PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
    this.essenceMax = essenceMax;
  }

  public void encode(Document document, PdfContentByte directContent,
                     IGenericCharacter character,
                     IGenericDescription description) throws DocumentException {
    // Essence box
    int distanceFromTop = 0;
    float essenceHeight = encodeEssence(directContent, character, description, distanceFromTop, CONTENT_HEIGHT);
    distanceFromTop += calculateBoxIncrement(essenceHeight);

    encodeCopyright(directContent);
  }

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop, float height) throws DocumentException {
    // TODO: Eliminate unchecked casting
    return encodeVariableBox(directContent, character, description,
                             (IPdfVariableContentBoxEncoder) getPartEncoder().getEssenceEncoder(),
                             1, 3, distanceFromTop, height);
  }
}