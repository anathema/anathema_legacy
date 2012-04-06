package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.abyssal.reporting.rendering.Anima2ndEditionEncoderFactory;
import net.sf.anathema.character.abyssal.reporting.rendering.Resonance2ndEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.type.CharacterType.ABYSSAL;

@RegisteredPartEncoder(characterType = ABYSSAL)
public class Extended2ndEditionAbyssalPartEncoder extends AbstractExtendedPartEncoder {

  public Extended2ndEditionAbyssalPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return new Resonance2ndEditionEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    BasicContent content = reportSession.createContent(BasicContent.class);
    return new Anima2ndEditionEncoderFactory().create(getResources(), content);
  }
}
