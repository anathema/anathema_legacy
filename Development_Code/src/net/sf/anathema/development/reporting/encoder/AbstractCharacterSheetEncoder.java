package net.sf.anathema.development.reporting.encoder;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.AbstractReportEncoder;
import net.sf.anathema.development.reporting.IReportEncoder;
import net.sf.anathema.development.reporting.encoder.page.IPageFormat;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.test.character.DemoDataFileProvider;

public abstract class AbstractCharacterSheetEncoder extends AbstractReportEncoder implements IReportEncoder {

  public AbstractCharacterSheetEncoder(IPageFormat pageFormat) {
    super(pageFormat);
  }

  private static final CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(
      new AnathemaResources(),
      new DemoDataFileProvider());

  protected final ICharacterTemplate getDefaultTemplate(CharacterType characterType) {
    return container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        characterType,
        ExaltedEdition.FirstEdition);
  }
}