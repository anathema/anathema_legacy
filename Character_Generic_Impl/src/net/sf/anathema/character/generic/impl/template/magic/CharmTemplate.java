package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharmTemplate implements ICharmTemplate {

  private final ICharmSet charmSet;
  private final List<String> alienAllowedCastes = new ArrayList<String>();
  private final IMartialArtsRules martialArtsRules;

  public CharmTemplate(
      MartialArtsLevel martialArtsLevel,
      ICharmCache charmProvider,
      CharacterType characterType,
      IExaltedEdition edition) {
    this(new DefaultMartialArtsRules(martialArtsLevel), CharmSet.createRegularCharmSet(
        charmProvider,
        characterType,
        edition));
  }

  public CharmTemplate(IMartialArtsRules rules, ICharmSet charmSet) {
    this.martialArtsRules = rules;
    this.charmSet = charmSet;
  }

  public final ICharm[] getCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules);
  }

  public final ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return charmSet.getMartialArtsCharms(rules);
  }

  public IMartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
  }

  public boolean knowsCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules).length > 0 || charmSet.getMartialArtsCharms(rules).length > 0;
  }

  public boolean isAllowedAlienCharms(ICasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}