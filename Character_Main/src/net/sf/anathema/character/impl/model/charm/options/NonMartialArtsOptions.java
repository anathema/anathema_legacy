package net.sf.anathema.character.impl.model.charm.options;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.charm.GroupedCharmIdMap;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonMartialArtsOptions implements ICharmIdMap, ICharmGroupArbitrator {

  private final ICharacterModelContext context;
  private final CharacterTypes characterTypes;
  private final ITemplateRegistry registry;
  private final List<ICharacterType> availableTypes = new ArrayList<>();
  private final Map<Identified, ICharmTree> treesByType = new HashMap<>();

  public NonMartialArtsOptions(ICharacterModelContext context, CharacterTypes characterTypes, ITemplateRegistry registry) {
    this.context = context;
    this.characterTypes = characterTypes;
    this.registry = registry;
    collectAvailableTypes();
    initCharmTreesForAvailableTypes();
  }

  private void collectAvailableTypes() {
    for (ICharacterType type : this.characterTypes.findAll()) {
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        availableTypes.add(type);
      }
    }
    availableTypes.remove(getNativeCharacterType());
    availableTypes.add(0, getNativeCharacterType());
  }

  public ICharmTree getCharmTrees(ICharacterType type) {
    return treesByType.get(type);
  }

  public Iterable<ICharacterType> getAvailableCharacterTypes() {
    return new ArrayList<>(availableTypes);
  }

  @Override
  public ICharm getCharmById(String charmId) {
    GroupedCharmIdMap aggregatedTrees = new GroupedCharmIdMap(treesByType.values());
    return aggregatedTrees.getCharmById(charmId);
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    IBasicCharacterData data = context.getBasicCharacterContext();
    ICharm[] allCharms = charmGroup.getAllCharms();
    if (getNativeCharmTemplate().isAllowedAlienCharms(data.getCasteType())) {
      return allCharms;
    }
    List<ICharm> charms = new ArrayList<>();
    for (ICharm charm : allCharms) {
      if (!charm.hasAttribute(IExtendedCharmData.EXCLUSIVE_ATTRIBUTE) || data.getCharacterType() == charm.getCharacterType()) {
        charms.add(charm);
      }
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  public ICharacterType getNativeCharacterType() {
    return context.getBasicCharacterContext().getCharacterType();
  }

  public ICharmTemplate getNativeCharmTemplate() {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    ITemplateType templateType = basicCharacterContext.getTemplateType();
    ICharacterTemplate template = registry.getTemplate(templateType);
    IMagicTemplate magicTemplate = template.getMagicTemplate();
    return magicTemplate.getCharmTemplate();
  }

  private void initCharmTreesForAvailableTypes() {
    for (ICharacterType type : availableTypes) {
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        treesByType.put(type, new CharmTree(charmTemplate));
      }
    }
  }

  private ICharmTemplate getCharmTemplate(ITemplateRegistry registry, ICharacterType type) {
    ICharacterTemplate defaultTemplate = registry.getDefaultTemplate(type);
    if (defaultTemplate == null) {
      return null;
    }
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
  }

  public ICharacterType getCharacterType(String characterTypeId) {
    if (characterTypeId == null) {
      return getNativeCharacterType();
    }
    return characterTypes.findById(characterTypeId);
  }

  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new ICharacterType[]{getNativeCharacterType()};
    }
    return availableTypes.toArray(new ICharacterType[availableTypes.size()]);
  }

  public boolean isAlienType(ICharacterType characterType) {
    ICharacterType nativeType = getNativeCharacterType();
    return !characterType.equals(nativeType);
  }
}
