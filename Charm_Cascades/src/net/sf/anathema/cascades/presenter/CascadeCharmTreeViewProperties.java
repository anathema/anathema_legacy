package net.sf.anathema.cascades.presenter;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.NullSpecialCharm;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.awt.Cursor;
import java.util.Map;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private IIdentificate type;
  private final ProxyRuleSet rules;
  private final ICharmCache cache;
  private final ICharacterGenerics generics;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules;

  public CascadeCharmTreeViewProperties(IResources resources, ICharacterGenerics generics,
                                        Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules,
                                        ProxyRuleSet selectedRuleSet, ICharmCache cache) {
    super(resources);
    this.charmMapsByRules = charmMapsByRules;
    this.generics = generics;
    this.rules = selectedRuleSet;
    this.cache = cache;
  }

  @Override
  protected ICharm getCharmById(String id) {
    ICharm charm = charmMapsByRules.get(rules.getDelegate()).get(type).getCharmById(id);
    if (charm == null) {
      charm = searchCharm(id);
    }
    return charm;
  }

  @Override
  protected ISpecialCharm getSpecialCharm(String id) {
    for (ISpecialCharm special : getSpecialCharmSet()) {
      if (special.getCharmId().equals(id)) {
        return special;
      }
    }
    return new NullSpecialCharm();
  }

  private ICharm searchCharm(final String charmId) {
    String[] idParts = charmId.split("\\."); //$NON-NLS-1$
    ICharacterType characterTypeId = CharacterType.getById(idParts[0]);
    ICharm[] charms = cache.getCharms(characterTypeId, rules);
    return ArrayUtilities.find(new Predicate<ICharm>() {
      @Override
      public boolean apply(ICharm candidate) {
        return candidate.getId().equals(charmId);
      }
    }, charms);
  }

  public void setCharmType(IIdentificate type) {
    this.type = type;
  }

  @Override
  public Cursor getCursor(String nodeId) {
    if (nodeId == null) {
      return getDefaultCursor();
    }
    return getControlCursor();
  }

  private ISpecialCharm[] getSpecialCharmSet() {
    if (type.getId().equals("MartialArts")) {
      return generics.getCharmProvider().getSpecialMartialArtsCharms(rules.getEdition());
    } else {
      try {
        return generics.getCharmProvider().getSpecialCharms((CharacterType) type, rules.getEdition());
      } catch (ClassCastException exception) {
        // assuming unique types do not have special charms for now
        return new ISpecialCharm[0];
      }
    }
  }
}