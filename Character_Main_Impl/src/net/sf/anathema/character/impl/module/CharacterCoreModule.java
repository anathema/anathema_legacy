package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.lib.registry.IRegistry;

public class CharacterCoreModule extends AbstractAnathemaModule {

  private IAnathemaResources anathemaResources;

  public CharacterCoreModule() {
    // Nothing to do
  }

  @Override
  public void initModelExtensionPoints(IRegistry<String, IExtensionPoint> registry, IAnathemaModel model) {
    super.initModelExtensionPoints(registry, model);
    CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(anathemaResources);
    registry.register(ICharacterGenericsExtension.ID, new CharacterGenericsExtension(container.getCharacterGenerics()));
  }

  // @Override
  // public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
  // CharmCache charmCache = CharmCache.getInstance();
  // for (CharacterType characterType : CharacterType.values()) {
  // for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
  // try {
  // ICharm[] charms = charmCache.getCharms(characterType, ruleSet);
  // for (ICharm charm : charms) {
  // final String durationString = charm.getDuration().getText(resources);
  // if (durationString.contains("#")) {
  // System.err.println(ruleSet + " - " + charm.getId());
  // }
  // }
  // }
  // catch (Exception e) {
  // // e.printStackTrace();
  // }
  // }
  // }
  // }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    if (anathemaResources != null) {
      return;
    }
    this.anathemaResources = resources;
    // TODO: Resource-Registrierung in die jeweiligen Module auslagern?
    resources.addStringResourceHandler(createStringProvider("Health", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Abyssal", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Abyssal", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Dragon-Blooded", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Dragon-Blooded", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Solar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Lunar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Lunar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Solar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Solar_SecondEdition", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharacterControl", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Sidereal", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Sidereal", resources.getLocale())); //$NON-NLS-1$       
    resources.addStringResourceHandler(createStringProvider("Charmgroups", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharmDuration", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Traits", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Cascades", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharmTreeView", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("MeritsFlaws", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Intimacies", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharacterSheet", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Equipment", resources.getLocale())); //$NON-NLS-1$
  }
}