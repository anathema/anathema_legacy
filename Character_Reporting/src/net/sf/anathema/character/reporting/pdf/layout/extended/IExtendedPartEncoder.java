package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface IExtendedPartEncoder {

  IBoxContentEncoder getAnimaEncoder();

  IBoxContentEncoder getEssenceEncoder();

  IBoxContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey);

  IBoxContentEncoder getGreatCurseEncoder();

  IBoxContentEncoder getHealthEncoder();

  IBoxContentEncoder getMovementEncoder();

  boolean hasMagicPage();

  IVariableBoxContentEncoder[] getAdditionalFirstPageEncoders();

  IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);
}
