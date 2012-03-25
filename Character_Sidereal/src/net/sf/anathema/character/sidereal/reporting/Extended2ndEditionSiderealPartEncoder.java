package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Sidereal2ndEditionDetailsPageEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.sidereal.reporting.rendering.greatcurse.FlawedFateEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.SIDEREAL;

@RegisteredPartEncoder(characterType = SIDEREAL, edition = SecondEdition)
public class Extended2ndEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return new FlawedFateEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[]{new Sidereal2ndEditionDetailsPageEncoder(getResources(), getFontSize(), configuration)};
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    BasicContent content = reportSession.createContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
