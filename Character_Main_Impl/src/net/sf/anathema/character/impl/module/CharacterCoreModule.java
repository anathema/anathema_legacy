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
  // // for (CharacterType characterType : CharacterType.values()) {
  // IIdentificate characterType = CharmCache.MARTIAL_ARTS_TYPE;
  // for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
  // ICharm[] charms = new ICharm[0];
  // try {
  // charms = charmCache.getCharms(characterType, ruleSet);
  // }
  // catch (Exception e) {
  // System.err.println(characterType + "-" + ruleSet);
  // }
  // System.out.println(characterType + "-" + ruleSet);
  // for (ICharm charm : charms) {
  // final String sourceString = charm.getSource().getSource();
  // final String pageString = charm.getSource().getPage();
  // final String string = sourceString + "." + charm.getId() + ".Page=" + pageString;
  // if (ruleSet != ExaltedRuleSet.PowerCombat || sourceString.startsWith("Players")) {
  // System.out.println(string);
  // }
  // if (string.contains(" ") && !(charm.getId().equals("Solar.Spirit Weapons"))) {
  // throw new RuntimeException();
  // }
  //        // }
  //
  //      }
  //    }
  //  }

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
    resources.addStringResourceHandler(createStringProvider("Charms_Abyssal_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Dragon-Blooded", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Dragon-Blooded", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Dragon-Blooded_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Solar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Lunar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Lunar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Lunar_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Solar", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Solar_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Solar_SecondEdition", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharacterControl", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Sidereal", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Sidereal", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_Sidereal_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charmgroups", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharmDuration", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Charms_MartialArts_Pages", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Traits", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Cascades", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharmTreeView", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("MeritsFlaws", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Intimacies", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("CharacterSheet", resources.getLocale())); //$NON-NLS-1$
    resources.addStringResourceHandler(createStringProvider("Equipment", resources.getLocale())); //$NON-NLS-1$
  }
}