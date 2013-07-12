package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.concept.CasteType;

import java.util.ArrayList;
import java.util.List;

public class CharmTemplateImpl implements CharmTemplate {

  private final CharmSet charmSet;
  private boolean canLearnCharms;
  private final List<String> alienAllowedCastes = new ArrayList<>();
  private final MartialArtsRules martialArtsRules;

  public CharmTemplateImpl(MartialArtsRules rules, CharmSet charmSet, boolean canLearnCharms) {
    this.martialArtsRules = rules;
    this.charmSet = charmSet;
    this.canLearnCharms = canLearnCharms;
  }

  @Override
  public final Charm[] getCharms() {
    return charmSet.getCharms();
  }

  @Override
  public final Charm[] getMartialArtsCharms() {
    return charmSet.getMartialArtsCharms();
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
  }

  @Override
  public boolean canLearnCharms() {
    return canLearnCharms;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}