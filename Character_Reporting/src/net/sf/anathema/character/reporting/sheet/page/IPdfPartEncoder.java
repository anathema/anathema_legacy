package net.sf.anathema.character.reporting.sheet.page;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPartEncoder {

  public void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds);

  public void encodeAttributes(
      PdfContentByte directContent,
      SmartRectangle contentBounds,
      IGroupedTraitType[] groups,
      IGenericTraitCollection traitCollection);

  public void encodeEditionSpecificFirstPagePart(PdfContentByte directContent, SmartRectangle restBounds);

  public void encodeEssence(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds);

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds);

  public BaseFont getBaseFont();

  public IResources getResources();
}