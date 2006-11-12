package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class CharmTemplate implements ICharmTemplate {

  private final MartialArtsLevel martialArtsLevel;
  private final ICharmSet charmSet;
  private final boolean highLevelAtCreation;
  private final List<String> alienAllowedCastes = new ArrayList<String>();

  public CharmTemplate(
      MartialArtsLevel martialArtsLevel,
      ICharmCache charmProvider,
      CharacterType characterType,
      IExaltedEdition edition) throws PersistenceException {
    this(martialArtsLevel, false, CharmSet.createRegularCharmSet(charmProvider, characterType, edition));
  }

  public CharmTemplate(MartialArtsLevel level, boolean highLevelAtCreation, ICharmSet charmSet) {
    this.martialArtsLevel = level;
    this.highLevelAtCreation = highLevelAtCreation;
    this.charmSet = charmSet;
  }

  public final ICharm[] getCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules);
  }

  public final ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return charmSet.getMartialArtsCharms(rules);
  }

  public MartialArtsLevel getMartialArtsLevel() {
    return martialArtsLevel;
  }

  public boolean knowsCharms(IExaltedRuleSet rules) {
    return charmSet.getCharms(rules).length > 0 || charmSet.getMartialArtsCharms(rules).length > 0;
  }

  public boolean isMartialArtsCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    int comparedLevel = MartialArtsUtilities.getLevel(martialArtsCharm).compareTo(getMartialArtsLevel());
    if (comparedLevel <= 0) {
      return true;
    }
    if (comparedLevel == 1) {
      return isExperienced || mayLearnHighLevelAtCreation();
    }
    return false;
  }

  protected boolean mayLearnHighLevelAtCreation() {
    return highLevelAtCreation;
  }

  public boolean isAllowedAlienCharms(ICasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}