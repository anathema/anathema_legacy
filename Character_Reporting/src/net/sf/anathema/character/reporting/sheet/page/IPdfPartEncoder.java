package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPartEncoder {

  public void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds)
      throws DocumentException;

  public void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] groups,
      IGenericTraitCollection traitCollection) throws DocumentException;

  public void encodeEditionSpecificFirstPagePart(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop) throws DocumentException;

  public void encodeEssence(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds)
      throws DocumentException;

  public void encodePersonalInfos(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      Bounds infoBounds) throws DocumentException;

  public BaseFont getBaseFont();

  public IResources getResources();
}