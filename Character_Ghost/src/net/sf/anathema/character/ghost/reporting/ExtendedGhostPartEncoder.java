package net.sf.anathema.character.ghost.reporting;

import net.sf.anathema.character.ghost.reporting.rendering.FetterEncoder;
import net.sf.anathema.character.ghost.reporting.rendering.PassionEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.GHOST;

@RegisteredPartEncoder(characterType = GHOST, edition = SecondEdition)
public class ExtendedGhostPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public ExtendedGhostPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new FetterEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    return new PassionEncoder();
  }
}
