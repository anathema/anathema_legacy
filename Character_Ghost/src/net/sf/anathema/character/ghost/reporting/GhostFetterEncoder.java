package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class GhostFetterEncoder implements IBoxContentEncoder {

  private final PdfTraitEncoder traitEncoder;
  public GhostFetterEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Ghost.Fetters"; //$NON-NLS-1$
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
	  IGhostFettersModel model = (IGhostFettersModel) reportContent.getCharacter().getAdditionalModel(GhostFettersTemplate.ID);
	  float groupSpacing = traitEncoder.getTraitHeight() / 2;
	  float x = graphics.getBounds().x;
	  float y = graphics.getBounds().getMaxY() - 2 * groupSpacing;
	  int maximum = 5;
	  float width = graphics.getBounds().getWidth() * 1 / 2;
	  for (Fetter fetter : model.getFetters())
	  {
	      String traitLabel = fetter.getName();
	      int value = fetter.getCurrentValue();
	      Position position = new Position(x, y);
	      y -= traitEncoder.encodeWithText(graphics.getDirectContent(),
	          traitLabel,
	          position,
	          width,
	          value,
	          maximum);
	      
	      if (y < graphics.getBounds().getMinY())
	      {
	    	  y = graphics.getBounds().getMaxY() - 2 * groupSpacing;
	    	  x += graphics.getBounds().width / 2;
	      }
	    }
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
