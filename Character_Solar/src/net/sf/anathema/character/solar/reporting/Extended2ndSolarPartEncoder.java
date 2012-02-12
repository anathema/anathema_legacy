package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.solar.reporting.rendering.AnimaEncoderFactory;
import net.sf.anathema.character.solar.reporting.rendering.VirtueFlawEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.SOLAR;

@RegisteredPartEncoder(characterType = SOLAR, edition = SecondEdition)
public class Extended2ndSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndSolarPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new VirtueFlawEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
