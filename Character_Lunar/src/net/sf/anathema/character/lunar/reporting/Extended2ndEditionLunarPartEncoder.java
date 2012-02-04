package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.reporting.layout.Lunar2ndEditionAdditionalPageEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.GreatCurse2ndEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.LUNAR;

@RegisteredPartEncoder(characterType = LUNAR, edition = SecondEdition)
public class Extended2ndEditionLunarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionLunarPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new GreatCurse2ndEditionEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    return new PageEncoder[]{new Lunar2ndEditionAdditionalPageEncoder(encoderRegistry, getResources(), configuration)};
  }
}
