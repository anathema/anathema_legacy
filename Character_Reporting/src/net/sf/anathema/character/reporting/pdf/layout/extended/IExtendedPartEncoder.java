package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;

public interface IExtendedPartEncoder {

  ContentEncoder getAnimaEncoder(ReportContent reportContent);

  ContentEncoder getEssenceEncoder();

  ContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey);

  ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content);

  ContentEncoder getHealthEncoder();

  ContentEncoder getMovementEncoder();

  boolean hasMagicPage();

  IVariableContentEncoder[] getAdditionalFirstPageEncoders();

  PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration);
}
