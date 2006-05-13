package net.sf.anathema.character.reporting.encoder;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPartEncoder {

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds);

  public void encodeAttributes(
      PdfContentByte directContent,
      SmartRectangle attributesContentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection);

  public BaseFont getBaseFont();

  public void encodeEditionSpecificFirstPagePart(PdfContentByte directContent, SmartRectangle restBounds);

  public IResources getResources();
}