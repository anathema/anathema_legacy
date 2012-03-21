package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.List;

public class CharmTemplate implements ICharmTemplate {

  private final ICharmSet charmSet;
  private final List<String> alienAllowedCastes = new ArrayList<String>();
  private final IUniqueCharmType uniqueCharmType;
  private final IMartialArtsRules martialArtsRules;

  public CharmTemplate(MartialArtsLevel martialArtsLevel, ICharmCache charmProvider, ICharacterType characterType,
                       IExaltedEdition edition) {
    this(new DefaultMartialArtsRules(martialArtsLevel),
            CharmSet.createRegularCharmSet(charmProvider, characterType, null, edition));
  }

  public CharmTemplate(IMartialArtsRules rules, ICharmSet charmSet) {
    this(rules, charmSet, null);
  }

  public CharmTemplate(IMartialArtsRules rules, ICharmSet charmSet, IUniqueCharmType uniqueCharms) {
    this.martialArtsRules = rules;
    this.charmSet = charmSet;
    this.uniqueCharmType = uniqueCharms;
  }

  @Override
  public final ICharm[] getCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules);
  }
  
  @Override
  public final ICharm[] getUniqueCharms(IExaltedRuleSet rules) {
	return charmSet.getUniqueCharms(rules);
  }

  @Override
  public final IUniqueCharmType getUniqueCharmType() {
    return uniqueCharmType;
  }

  @Override
  public final ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return charmSet.getMartialArtsCharms(rules);
  }

  @Override
  public IMartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
  }

  @Override
  public boolean hasUniqueCharms() {
    return uniqueCharmType != null;
  }

  @Override
  public boolean canLearnCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules).length > 0 || charmSet.getMartialArtsCharms(rules).length > 0;
  }

  @Override
  public boolean isAllowedAlienCharms(ICasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}