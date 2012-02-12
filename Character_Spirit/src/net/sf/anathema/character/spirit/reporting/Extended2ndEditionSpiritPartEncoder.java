package net.sf.anathema.character.spirit.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.SPIRIT;

@RegisteredPartEncoder(characterType = SPIRIT, edition = SecondEdition)
public class Extended2ndEditionSpiritPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionSpiritPartEncoder(IResources resources) {
    super(resources);
  }

  // TODO: This should be properly edited out, not just nulled out.
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new NullBoxContentEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new SpiritAnimaEncoderFactory().create(getResources(), content);
  }
}
