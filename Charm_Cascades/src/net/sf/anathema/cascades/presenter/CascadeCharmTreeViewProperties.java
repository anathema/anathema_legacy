package net.sf.anathema.cascades.presenter;

import java.awt.Cursor;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private IIdentificate type;
  private IExaltedRuleSet rules;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules;

  public CascadeCharmTreeViewProperties(
      IResources resources,
      Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules) {
    super(resources);
    this.charmMapsByRules = charmMapsByRules;

  }

  public boolean isNodeSelected(String charmId) {
    return true;
  }

  public Cursor getAddCursor() {
    return Cursor.getDefaultCursor();
  }

  public Cursor getRemoveCursor() {
    return Cursor.getDefaultCursor();
  }

  public boolean isNodeSelectable(String charmId) {
    return true;
  }

  @Override
  protected ICharm getCharmById(String id) {
    ICharm charm = charmMapsByRules.get(rules).get(type).getCharmById(id);
    if (charm == null) {
      charm = searchCharm(id);
    }
    return charm;
  }

  private ICharm searchCharm(final String charmId) {
    String[] idParts = charmId.split("\\."); //$NON-NLS-1$
    ICharacterType characterTypeId = CharacterType.getById(idParts[0]);
    ICharm[] charms = CharmCache.getInstance().getCharms(characterTypeId, rules);
    ICharm charm = ArrayUtilities.find(new Predicate<ICharm>() {
      public boolean evaluate(ICharm candidate) {
        return candidate.getId().equals(charmId);
      }
    }, charms);
    return charm;
  }

  public boolean isNodeDeselectable(String charmId) {
    return false;
  }

  public void setCharmType(IIdentificate type) {
    this.type = type;
  }

  public void setRules(IExaltedRuleSet rules) {
    this.rules = rules;
  }
}