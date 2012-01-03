package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.DotBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final ExtendedEncodingRegistry registry;

  public Extended1stEditionMortalPartEncoder(IResources resources, BaseFont baseFont, ExtendedEncodingRegistry registry) {
    super(resources, baseFont);
    this.registry = registry;
  }

  public ContentEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources());
  }

  public ContentEncoder getEssenceEncoder() {
    return new ExperienceBoxContentEncoder();
  }

  public ContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    return new DotBoxContentEncoder(trait, traitMax, traitHeaderKey);
  }

  public boolean hasSecondPage() {
    return false;
  }

  public boolean hasMagicPage() {
    return false;
  }

  public ContentEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}
