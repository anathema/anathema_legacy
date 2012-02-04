package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.infernal.reporting.rendering.AnimaEncoderFactory;
import net.sf.anathema.character.infernal.reporting.rendering.UrgeEncoder;
import net.sf.anathema.character.infernal.reporting.rendering.YoziListEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public ExtendedInfernalPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder(ExtendedEncodingRegistry registry) {
    return new UrgeEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportContent reportContent) {
    BasicContent content = reportContent.createSubContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }

  public IVariableContentEncoder[] getAdditionalFirstPageEncoders() {
    return new IVariableContentEncoder[]{new YoziListEncoder()};
  }
}
