package net.sf.anathema.character.ghost.reporting;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class GhostPassionEncoder implements IBoxContentEncoder {

  private final PdfTraitEncoder traitEncoder;
  private final IResources resources;

  public GhostPassionEncoder(BaseFont baseFont, IResources resources) {
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
    this.resources = resources;
  }

  public String getHeaderKey(ReportContent content) {
    return "Ghost.Passions"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    IGhostPassionsModel model = (IGhostPassionsModel) reportContent.getCharacter().getAdditionalModel(GhostPassionsTemplate.ID);
    float groupSpacing = traitEncoder.getTraitHeight() / 2;
    float x = bounds.x;
    float y = bounds.getMaxY() - 2 * groupSpacing;
    int maximum = 5;
    float width = bounds.getWidth();
    for (VirtueType virtue : VirtueType.values()) {
      ISubTraitContainer container = model.getPassionContainer(virtue);
      String virtueString = resources.getString(virtue.getId());
      for (ISubTrait passion : container.getSubTraits()) {
        String traitLabel = "(" + virtueString + ") " + passion.getName();
        int value = passion.getCurrentValue();
        Position position = new Position(x, y);
        y -= traitEncoder.encodeWithText(graphics, traitLabel, position, width, value, maximum);
      }
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
