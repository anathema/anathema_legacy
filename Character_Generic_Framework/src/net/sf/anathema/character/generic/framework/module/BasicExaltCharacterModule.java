package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.unsupported.UnsupportedDragonKingTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EssenceUserBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class BasicExaltCharacterModule extends NullObjectCharacterModuleAdapter {

  public static final String BACKGROUND_ID_FACE = "Face"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_MANSE = "Manse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_ARTIFACT = "Artifact"; //$NON-NLS-1$

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    backgroundRegistry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_ARTIFACT, templateRegistry));
    backgroundRegistry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_FACE, templateRegistry));
    backgroundRegistry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_MANSE, templateRegistry));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    CharmCache charmProvider = CharmCache.getInstance();
    try {
      characterGenerics.getTemplateRegistry().register(new UnsupportedDragonKingTemplate(charmProvider));
    }
    catch (PersistenceException e) {
      e.printStackTrace();
    }
  }
}