package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.magic.IExtendedCharmData.EXCLUSIVE_ATTRIBUTE;

public class ArbitratorForAlienAndExclusiveCharms implements ICharmGroupArbitrator {

  private final ICharmTemplate template;
  private final ICharacterModelContext context;

  public ArbitratorForAlienAndExclusiveCharms(ICharmTemplate template, ICharacterModelContext context) {
    this.template = template;
    this.context = context;
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    ICharm[] allCharms = charmGroup.getAllCharms();
    if (characterMayLearnAlienCharms()) {
      return allCharms;
    }
    return nonExclusiveCharmsAndCharmsForOwnCharacterType(allCharms);
  }

  private ICharm[] nonExclusiveCharmsAndCharmsForOwnCharacterType(ICharm[] allCharms) {
    List<ICharm> charms = new ArrayList<>();
    for (ICharm charm : allCharms) {
      boolean isExclusiveCharm = charm.hasAttribute(EXCLUSIVE_ATTRIBUTE);
      if (!isExclusiveCharm) {
        charms.add(charm);
      }
      boolean isCharmForCharacterType = new CharmHasSameTypeAsCharacter(context).apply(charm);
      if (isCharmForCharacterType) {
        charms.add(charm);
      }
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  private boolean characterMayLearnAlienCharms() {
    IBasicCharacterData data = context.getBasicCharacterContext();
    return template.isAllowedAlienCharms(data.getCasteType());
  }
}