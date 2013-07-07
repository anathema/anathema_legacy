package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.ValuedTraitReference;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.AbstractNamedTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.INamedTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.hero.abilities.sheet.content.SpecialtiesContentCandidate;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialtiesEncoder extends AbstractNamedTraitEncoder implements INamedTraitEncoder {

  private final int specialtyCount;

  public SpecialtiesEncoder(Resources resources, PdfTraitEncoder encoder, int specialtyCount) {
    super(resources, encoder);
    this.specialtyCount = specialtyCount;
  }

  @Override
  public float encode(SheetGraphics graphics, ReportSession session, Position position, float width, float height) {
    SpecialtiesContentCandidate content = new SpecialtiesContentCandidate(session.getHero());
    String title = getResources().getString("Sheet.AbilitySubHeader.Specialties");
    List<ValuedTraitReference> references = new ArrayList<>();
    for (IIdentifiedTraitTypeGroup group : AbilityModelFetcher.fetch(session.getHero()).getAbilityTypeGroups()) {
      for (TraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(content.getSpecialties(traitType), traitType));
      }
    }
    ValuedTraitReference[] specialties = references.toArray(new ValuedTraitReference[references.size()]);
    if (specialtyCount > 0) {
      return _drawNamedTraitSection(graphics, title, specialties, position, width, specialtyCount, 3);
    } else {
      return drawNamedTraitSection(graphics, title, specialties, position, width, height, 3);
    }
  }
}
