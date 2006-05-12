package net.sf.anathema.development.character.reporting;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPartEncoder {

  public void encodeAttributes(
      PdfContentByte directContent,
      SmartRectangle attributesContentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection);

  public BaseFont getBaseFont();
}