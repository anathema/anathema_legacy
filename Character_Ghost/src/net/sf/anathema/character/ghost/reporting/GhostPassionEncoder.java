package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class GhostPassionEncoder implements IPdfContentBoxEncoder {

	  private final PdfTraitEncoder traitEncoder;
	  private final IResources resources;

	  public GhostPassionEncoder(BaseFont baseFont, IResources resources) {
	    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
	    this.resources = resources;
	  }

	  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
	    return "Ghost.Passions"; //$NON-NLS-1$
	  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
	  IGhostPassionsModel model = (IGhostPassionsModel) character.getAdditionalModel(GhostPassionsTemplate.ID);
	  float groupSpacing = traitEncoder.getTraitHeight() / 2;
	  float x = bounds.x;
	  float y = bounds.getMaxY() - 2 * groupSpacing;
	  int maximum = 5;
	  float width = bounds.getWidth();
	  for (VirtueType virtue : VirtueType.values())
	  {
		  ISubTraitContainer container = model.getPassionContainer(virtue);
		  String virtueString = resources.getString(virtue.getId());
		  for (ISubTrait passion : container.getSubTraits())
		  {
			  String traitLabel = "(" + virtueString + ") " + passion.getName();
		      int value = passion.getCurrentValue();
		      Position position = new Position(x, y);
		      y -= traitEncoder.encodeWithText(
		          directContent,
		          traitLabel,
		          position,
		          width,
		          value,
		          maximum);
		  }
	  }
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
