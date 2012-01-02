package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final SimpleEncodingRegistry registry;

  public Simple1stEditionMortalPartEncoder(IResources resources, SimpleEncodingRegistry registry) {
    super(resources);
    this.registry = registry;
  }

  public IBoxContentEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources());
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new ExperienceBoxContentEncoder();
  }

  public boolean hasMagicPage() {
    return false;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
