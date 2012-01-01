package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final SimpleEncodingRegistry registry;

  public FirstEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, SimpleEncodingRegistry registry) {
    super(resources, baseFont, symbolBaseFont);
    this.registry = registry;
  }

  public IBoxContentEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources());
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new ExperienceBoxContentEncoder();
  }

  public boolean hasSecondPage() {
    return false;
  }

  public boolean hasMagicPage() {
    return false;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
