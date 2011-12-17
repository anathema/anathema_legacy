package net.sf.anathema.character.ghost.reporting.extended;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;

public class GhostFetterEncoder implements IPdfContentBoxEncoder {

  private final PdfTraitEncoder traitEncoder;
  public GhostFetterEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Ghost.Fetters"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
	  IGhostFettersModel model = (IGhostFettersModel) character.getAdditionalModel(GhostFettersTemplate.ID);
	  float groupSpacing = traitEncoder.getTraitHeight() / 2;
	  float x = bounds.x;
	  float y = bounds.getMaxY() - 2 * groupSpacing;
	  int maximum = 5;
	  float width = bounds.getWidth() * 1 / 2;
	  for (Fetter fetter : model.getFetters())
	  {
	      String traitLabel = fetter.getName();
	      int value = fetter.getCurrentValue();
	      Position position = new Position(x, y);
	      y -= traitEncoder.encodeWithText(
	          directContent,
	          traitLabel,
	          position,
	          width,
	          value,
	          maximum);
	      
	      if (y < bounds.getMinY())
	      {
	    	  y = bounds.getMaxY() - 2 * groupSpacing;
	    	  x += bounds.width / 2;
	      }
	    }
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
