package net.sf.anathema.character.intimacies.reporting.extended;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;

import static net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;

public class PdfIntimacyEncoder implements IPdfContentBoxEncoder {
  // TODO: Give this and PdfBackgroundEncoder a common base class, which may be more broadly useful.

  private final PdfTraitEncoder traitEncoder;

  public PdfIntimacyEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Intimacies"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    float yPosition = bounds.getMaxY() - LINE_HEIGHT;
    
    int maxValue = character.getTraitCollection().getTrait(VirtueType.Conviction).getCurrentValue();
    boolean printZeroIntimacies = AnathemaCharacterPreferences.getDefaultPreferences().printZeroIntimacies();
    
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getAdditionalModel(IntimaciesTemplate.ID);
    IIntimaciesModel model = additionalModel.getIntimaciesModel();
    for (IIntimacy intimacy : model.getEntries()) {
      ITrait intimacyTrait = intimacy.getTrait();
      if (yPosition < bounds.getMinY()) {
        return;
      }
      if (!printZeroIntimacies && intimacyTrait.getCurrentValue() == 0) {
        continue;
      }
      traitEncoder.encodeWithText(directContent, intimacy.getName(),
                                  new Position(bounds.x, yPosition), bounds.width,
                                  intimacyTrait.getCurrentValue(), maxValue);
      yPosition -= LINE_HEIGHT;
    }
    encodeEmptyLines(directContent, bounds, yPosition, maxValue);
  }

  private void encodeEmptyLines(PdfContentByte directContent, Bounds bounds, float yPosition, int maxValue) {
    while (yPosition > bounds.getMinY()) {
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithLine(directContent, position, bounds.width, 0, maxValue);
      yPosition -= LINE_HEIGHT;
    }
  }
  
  public boolean hasContent(IGenericCharacter character) {
	  return true;
  }
}
