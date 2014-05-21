package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.abilities.sheet.content.SpecialtiesContentCandidate;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;
import net.sf.anathema.hero.traits.sheet.encoder.AbstractNamedTraitEncoder;
import net.sf.anathema.hero.traits.sheet.encoder.INamedTraitEncoder;

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
    for (IdentifiedTraitTypeList group : AbilityModelFetcher.fetch(session.getHero()).getTraitTypeList()) {
      for (TraitType traitType : group.getAll()) {
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
