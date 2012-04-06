package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.reporting.pdf.PageSize;

public interface IExtendedPartEncoder {

  ContentEncoder getAnimaEncoder(ReportSession reportSession);

  ContentEncoder getEssenceEncoder();

  ContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey);

  ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session);

  ContentEncoder getHealthEncoder();

  ContentEncoder getMovementEncoder();

  IVariableContentEncoder[] getAdditionalFirstPageEncoders();

  PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageSize pageSize);
}
