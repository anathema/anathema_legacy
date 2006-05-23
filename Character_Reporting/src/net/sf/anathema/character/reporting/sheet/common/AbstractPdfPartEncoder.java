package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfPartEncoder extends AbstractPdfEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  public AbstractPdfPartEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    this.baseFont = baseFont;
    this.essenceMax = essenceMax;
    this.resources = resources;
  }

  @Override
  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds) {
    PdfAbilitiesEncoder encoder = new PdfAbilitiesEncoder(getBaseFont(), getResources(), essenceMax);
    encoder.encodeAbilities(directContent, character, contentBounds);
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    PdfAttributesEncoder encoder = new PdfAttributesEncoder(getBaseFont(), getResources(), essenceMax);
    encoder.encodeAttributes(directContent, contentBounds, attributeGroups, traitCollection);
  }

  public final IResources getResources() {
    return resources;
  }

  public void encodeEssence(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds) {
    PdfEssenceEncoder encoder = new PdfEssenceEncoder(getBaseFont(), getResources(), essenceMax);
    encoder.encodeEssence(directContent, character, contentBounds);
  }
}