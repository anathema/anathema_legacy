package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Position;
import net.sf.anathema.hero.traits.sheet.encoder.FavorableTraitContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.abilities.sheet.content.AbilitiesContent;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;

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
