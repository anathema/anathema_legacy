package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;

public interface IExtendedPartEncoder {

  ContentEncoder getAnimaEncoder(ReportSession reportSession);

  ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session);

  IVariableContentEncoder[] getAdditionalFirstPageEncoders();
}
