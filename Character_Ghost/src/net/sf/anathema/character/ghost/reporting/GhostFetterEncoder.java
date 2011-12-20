package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class GhostFetterEncoder implements IBoxContentEncoder {

  private final PdfTraitEncoder traitEncoder;
  public GhostFetterEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  public String getHeaderKey(ReportContent content) {
    return "Ghost.Fetters"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
	  IGhostFettersModel model = (IGhostFettersModel) reportContent.getCharacter().getAdditionalModel(GhostFettersTemplate.ID);
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
	      y -= traitEncoder.encodeWithText(graphics,
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
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
