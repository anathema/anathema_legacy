package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.SpecialtiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class AbilitiesTwoColumnEncoder extends FavorableTraitContentEncoder<AbilitiesContent> {

  private Resources resources;

  public AbilitiesTwoColumnEncoder(Resources resources) {
    super(AbilitiesContent.class);
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    float columnWidth = (bounds.getWidth() - PADDING - PADDING) / 2f;
    Bounds firstColumnBounds = new Bounds(bounds.x, bounds.y, columnWidth, bounds.height);
    super.encode(graphics, reportSession, firstColumnBounds);
    float secondColumnX = bounds.x + columnWidth + PADDING + PADDING;
    float craftHeight = bounds.height * 2f / 5f;
    float specialtiesHeight = bounds.height - craftHeight - PADDING;
    Position specialtiesPosition = new Position(secondColumnX, bounds.y + specialtiesHeight + PADDING);
    new SpecialtiesEncoder(resources, getTraitEncoder(), 17)
            .encode(graphics, reportSession, specialtiesPosition, columnWidth, specialtiesHeight);
  }
}
