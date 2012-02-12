package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.db.reporting.rendering.AnimaEncoderFactory;
import net.sf.anathema.character.db.reporting.rendering.GreatCurse2ndEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.DB;

@RegisteredPartEncoder(characterType = DB, edition = SecondEdition)
public class Extended2ndEditionDbPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionDbPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportContent content) {
    return new GreatCurse2ndEditionEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }
}
