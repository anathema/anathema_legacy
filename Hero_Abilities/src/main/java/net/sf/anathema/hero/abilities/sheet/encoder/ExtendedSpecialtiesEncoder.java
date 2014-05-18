package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.abilities.sheet.content.SpecialtiesContentCandidate;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;
import net.sf.anathema.hero.traits.sheet.encoder.AbstractNamedTraitEncoder;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TEXT_PADDING;

public class ExtendedSpecialtiesEncoder extends AbstractNamedTraitEncoder implements ContentEncoder {

  public ExtendedSpecialtiesEncoder(Resources resources) {
    super(resources, PdfTraitEncoder.createSmallTraitEncoder());
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    SpecialtiesContentCandidate content = new SpecialtiesContentCandidate(reportSession.getHero());
    List<ValuedTraitReference> references = new ArrayList<>();
    for (IIdentifiedTraitTypeGroup group :  AbilityModelFetcher.fetch(reportSession.getHero()).getAbilityTypeGroups()) {
      for (TraitType traitType : group.getAll()) {
        Collections.addAll(references, getTraitReferences(content.getSpecialties(traitType), traitType));
      }
    }
    ValuedTraitReference[] specialties = references.toArray(new ValuedTraitReference[references.size()]);

    int lineCount = getLineCount(null, bounds.height);
    ValuedTraitReference[] leftSpecialties = Arrays.copyOfRange(specialties, 0, Math.min(specialties.length, lineCount));
    ValuedTraitReference[] rightSpecialties = Arrays.copyOfRange(specialties, leftSpecialties.length, specialties.length);

    float columnWidth = (bounds.width - PADDING) / 2f;
    float columnHeight = bounds.height - TEXT_PADDING / 2f;
    float yPosition = bounds.getMaxY() - BARE_LINE_HEIGHT;

    float leftPosition = bounds.getMinX();
    drawNamedTraitSection(graphics, null, leftSpecialties, new Position(leftPosition, yPosition), columnWidth, columnHeight, 3);

    float rightPosition = leftPosition + columnWidth + PADDING;
    drawNamedTraitSection(graphics, null, rightSpecialties, new Position(rightPosition, yPosition), columnWidth, columnHeight, 3);
  }

  @Override
  public String getHeader(ReportSession session) {
    return getResources().getString("Sheet.Header.Specialties");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
