package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageFactory;
import net.sf.anathema.character.reporting.pdf.rendering.pages.RegisteredAdditionalPage;
import net.sf.anathema.lib.resources.IResources;

@RegisteredAdditionalPage
public class Lunar2ndEditionAdditionalPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageConfiguration configuration) {
    return new PageEncoder[] { new Lunar2ndEditionAdditionalPageEncoder(encoderRegistry, resources, configuration) };
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition() && content.isOfType(CharacterType.LUNAR);
  }
}
