package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  private SimpleEncodingRegistry registry;

  public Simple2ndEditionMortalPartEncoder(IResources resources, SimpleEncodingRegistry registry) {
    super(resources);
    this.registry = registry;
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources());
  }

  @Override
  public IBoxContentEncoder getEssenceEncoder() {
    return new ExperienceBoxContentEncoder();
  }

  @Override
  public IBoxContentEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
