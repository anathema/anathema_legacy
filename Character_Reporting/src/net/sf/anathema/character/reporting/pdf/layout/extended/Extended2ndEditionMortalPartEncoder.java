package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.MORTAL;

@RegisteredPartEncoder(characterType = MORTAL, edition = SecondEdition)
public class Extended2ndEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public Extended2ndEditionMortalPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    return null;
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return null;
  }

  @Override
  public boolean hasMagicPage() {
    return false;
  }
}
