package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.pdf.content.stats.IValuedTraitReference;
import net.sf.anathema.character.reporting.pdf.content.stats.NamedGenericTraitReference;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SUBSECTION_FONT_SIZE;

public abstract class AbstractNamedTraitEncoder {
  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;

  public AbstractNamedTraitEncoder(IResources resources, PdfTraitEncoder traitEncoder) {
    this.resources = resources;
    this.traitEncoder = traitEncoder;
  }

  protected IResources getResources() {
    return resources;
  }

  protected int getLineCount(String title, float height) {
    if (title != null) {
      height -= SUBSECTION_FONT_SIZE * 1.5f;
    }
    return (int) (height / traitEncoder.getTraitHeight());
  }

  protected float drawNamedTraitSection(SheetGraphics graphics, String title, IValuedTraitReference[] traits, Position position, float width, float height, int dotCount) {
    return _drawNamedTraitSection(graphics, title, traits, position, width, getLineCount(title, height), dotCount);
  }

  protected float _drawNamedTraitSection(SheetGraphics graphics, String title, IValuedTraitReference[] traits, Position position, float width, int lineCount, int dotCount) {
    float height = 0;
    if (title != null) {
      height = drawSubsectionHeader(graphics, title, position, width);
    }
    TraitInternationalizer internationalizer = new TraitInternationalizer(getResources());
    for (int index = 0; index < lineCount && index < traits.length; index++) {
      IValuedTraitReference trait = traits[index];
      String name = internationalizer.getSheetName(trait);
      Position traitPosition = new Position(position.x, position.y - height);
      int value = trait.getValue();
      traitEncoder.encodeWithText(graphics, name, traitPosition, width, value, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    for (int index = traits.length; index < lineCount; index++) {
      Position traitPosition = new Position(position.x, position.y - height);
      traitEncoder.encodeWithLine(graphics, traitPosition, width, 0, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    return height;
  }

  private final float drawSubsectionHeader(SheetGraphics graphics, String text, Position position, float width) {
    setSubsectionFont(graphics);
    graphics.drawText(text, new Position(position.x + width / 2, position.y), PdfContentByte.ALIGN_CENTER);
    return SUBSECTION_FONT_SIZE * 1.5f;
  }

  protected final void setSubsectionFont(SheetGraphics graphics) {
    graphics.setSubsectionFont();
  }

  protected final IValuedTraitReference[] getTraitReferences(INamedGenericTrait[] traits, ITraitType type) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (INamedGenericTrait trait : traits) {
      references.add(new NamedGenericTraitReference(trait, type));
    }
    return references.toArray(new IValuedTraitReference[references.size()]);
  }
}
