package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.BackgroundsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.DotBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  public Extended1stEditionMortalPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    return new BackgroundsEncoder(getResources());
  }

  @Override
  public ContentEncoder getEssenceEncoder() {
    return new ExperienceBoxContentEncoder();
  }

  @Override
  public ContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    return new DotBoxContentEncoder(trait, traitMax, getResources(), traitHeaderKey);
  }

  @Override
  public boolean hasMagicPage() {
    return false;
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
