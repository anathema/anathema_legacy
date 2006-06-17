package net.sf.anathema.test.character.main.impl.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.impl.magic.charm.CharmGroup;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.charm.LearningCharmGroup;
import net.sf.anathema.character.impl.model.context.magic.CreationCharmLearnStrategy;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.dummy.character.charm.DummyCharm;
import net.sf.anathema.dummy.character.charm.DummyLearnableArbitrator;
import net.sf.anathema.dummy.character.charm.DummyLearningCharmGroupContainer;
import net.sf.anathema.dummy.character.template.DummyCharmTemplate;
import net.sf.anathema.lib.testing.BasicTestCase;

public class LearningCharmGroupTest extends BasicTestCase {

  private DummyLearningCharmGroupContainer container = new DummyLearningCharmGroupContainer();

  private LearningCharmGroup createSolarMeleeGroup(ICharmLearnableArbitrator learnableArbitrator) {
    return createSolarGroup(learnableArbitrator, AbilityType.Melee.getId());
  }

  private LearningCharmGroup createSolarGroup(ICharmLearnableArbitrator learnableArbitrator, String groupId) {
    ICharmLearnStrategy learnSrategy = new CreationCharmLearnStrategy();
    CharmTree charmTree = new CharmTree(new DummyCharmTemplate(), ExaltedRuleSet.CoreRules);
    CharmGroup group = new CharmGroup(CharacterType.SOLAR, groupId, charmTree.getAllCharmsForGroup(groupId), false);
    return new LearningCharmGroup(learnSrategy, group, learnableArbitrator, container);
  }

  private LearningCharmGroup createSolarGroup(
      ICharmLearnableArbitrator learnableArbitrator,
      ICharmTree charmTree,
      String groupId) {
    ICharmLearnStrategy learnSrategy = new CreationCharmLearnStrategy();
    CharmGroup group = new CharmGroup(CharacterType.SOLAR, groupId, charmTree.getAllCharmsForGroup(groupId), false);
    return new LearningCharmGroup(learnSrategy, group, learnableArbitrator, container);
  }

  public void testIsLearnedCreationCharmOnCreation() throws Exception {
    ICharm learnableCharm = new DummyCharm("learnableDummyCharm"); //$NON-NLS-1$
    ICharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(
        new String[] { learnableCharm.getId() });
    LearningCharmGroup learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isLearned(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isLearned(learnableCharm));
  }

  public void testLearnedCreationCharmsUnlearnableOnCreation() throws Exception {
    ICharm learnableCharm = new DummyCharm("learnableDummyCharm"); //$NON-NLS-1$
    ICharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(
        new String[] { learnableCharm.getId() });
    LearningCharmGroup learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isUnlearnable(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isUnlearnable(learnableCharm));
  }

  public void testMultipleGroupsPrerequisiteCharms() throws Exception {
    String internalPrerequisiteId = "internalPrerquisite"; //$NON-NLS-1$
    String externalPrerequisiteId = "externalPrerquisite"; //$NON-NLS-1$
    String learCharmID = "learnCharm"; //$NON-NLS-1$
    DummyCharm internalPrerequisite = new DummyCharm(
        internalPrerequisiteId,
        new ICharm[0],
        new IGenericTrait[] { new ValuedTraitType(AbilityType.Melee, 1) });
    DummyCharm externalPrerequisite = new DummyCharm(
        externalPrerequisiteId,
        new ICharm[0],
        new IGenericTrait[] { new ValuedTraitType(AbilityType.Archery, 1) });
    DummyCharm learnCharm = new DummyCharm(
        learCharmID,
        new ICharm[] { internalPrerequisite, externalPrerequisite },
        new IGenericTrait[] { new ValuedTraitType(AbilityType.Melee, 1) });
    ICharmTree charmTree = new CharmTree(new ICharm[] { internalPrerequisite, externalPrerequisite, learnCharm });
    externalPrerequisite.addLearnFollowUpCharm(learnCharm);
    ICharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(new String[] {
        externalPrerequisiteId,
        internalPrerequisiteId,
        learCharmID });
    LearningCharmGroup internalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Melee.getId());
    LearningCharmGroup externalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Archery.getId());
    container.setLearningCharmGroups(new ILearningCharmGroup[] { internalGroup, externalGroup });
    assertFalse(externalGroup.isLearned(externalPrerequisite));
    assertFalse(internalGroup.isLearned(internalPrerequisite));
    assertFalse(internalGroup.isLearned(learnCharm));
    internalGroup.learnCharm(learnCharm, false);
    assertTrue(externalGroup.isLearned(externalPrerequisite));
    assertTrue(internalGroup.isLearned(internalPrerequisite));
    assertTrue(internalGroup.isLearned(learnCharm));
    externalGroup.forgetCharm(externalPrerequisite, false);
    assertFalse(externalGroup.isLearned(externalPrerequisite));
    assertTrue(internalGroup.isLearned(internalPrerequisite));
    assertFalse(internalGroup.isLearned(learnCharm));
  }
}