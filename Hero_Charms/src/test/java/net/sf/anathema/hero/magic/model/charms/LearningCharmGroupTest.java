package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmGroup;
import net.sf.anathema.hero.charms.model.ICharmLearnStrategy;
import net.sf.anathema.hero.charms.model.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.ILearningCharmGroup;
import net.sf.anathema.hero.charms.model.LearningCharmGroup;
import net.sf.anathema.hero.charms.model.options.CharmTree;
import net.sf.anathema.hero.charms.model.options.CharmTreeImpl;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.magic.dummy.DummyLearnableArbitrator;
import net.sf.anathema.hero.magic.dummy.DummyLearningCharmGroupContainer;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LearningCharmGroupTest {

  private DummyLearningCharmGroupContainer container = new DummyLearningCharmGroupContainer();

  private LearningCharmGroup createSolarMeleeGroup(IExtendedCharmLearnableArbitrator learnableArbitrator) {
    return createSolarGroup(learnableArbitrator, AbilityType.Melee.getId());
  }

  private LearningCharmGroup createSolarGroup(IExtendedCharmLearnableArbitrator learnableArbitrator, String groupId) {
    ICharmLearnStrategy learnStrategy = new CreationCharmLearnStrategy();
    CharmTreeImpl charmTree = new CharmTreeImpl(new Charm[0]);
    CharmGroup group = new CharmGroup(new DummyExaltCharacterType(), groupId,
            charmTree.getAllCharmsForGroup(groupId).toArray(new Charm[charmTree.getAllCharmsForGroup(groupId).size()]), false);
    return new LearningCharmGroup(learnStrategy, group, learnableArbitrator, container);
  }

  private LearningCharmGroup createSolarGroup(IExtendedCharmLearnableArbitrator learnableArbitrator, CharmTree charmTree, String groupId) {
    ICharmLearnStrategy learnSrategy = new CreationCharmLearnStrategy();
    CharmGroup group = new CharmGroup(new DummyExaltCharacterType(), groupId,
            charmTree.getAllCharmsForGroup(groupId).toArray(new Charm[charmTree.getAllCharmsForGroup(groupId).size()]), false);
    return new LearningCharmGroup(learnSrategy, group, learnableArbitrator, container);
  }

  @Test
  public void testIsLearnedCreationCharmOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getId());
    LearningCharmGroup learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isLearned(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isLearned(learnableCharm));
  }

  @Test
  public void testLearnedCreationCharmsUnlearnableOnCreation() throws Exception {
    Charm learnableCharm = new DummyCharm("learnableDummyCharm");
    IExtendedCharmLearnableArbitrator learnableArbitrator = new DummyLearnableArbitrator(learnableCharm.getId());
    LearningCharmGroup learningCharmGroup = createSolarMeleeGroup(learnableArbitrator);
    container.setLearningCharmGroup(learningCharmGroup);
    assertFalse(learningCharmGroup.isUnlearnable(learnableCharm));
    learningCharmGroup.toggleLearned(learnableCharm);
    assertTrue(learningCharmGroup.isUnlearnable(learnableCharm));
  }

  @Test
  public void testMultipleGroupsPrerequisiteCharms() throws Exception {
    String internalPrerequisiteId = "internalPrerquisite";
    String externalPrerequisiteId = "externalPrerquisite";
    String learCharmID = "learnCharm";
    DummyCharm internalPrerequisite =
            new DummyCharm(internalPrerequisiteId, new Charm[0], new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Melee, 1)});
    DummyCharm externalPrerequisite =
            new DummyCharm(externalPrerequisiteId, new Charm[0], new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Archery, 1)});
    DummyCharm learnCharm = new DummyCharm(learCharmID, new Charm[]{internalPrerequisite, externalPrerequisite},
            new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Melee, 1)});
    CharmTree charmTree = new CharmTreeImpl(new Charm[]{internalPrerequisite, externalPrerequisite, learnCharm});
    externalPrerequisite.addLearnFollowUpCharm(learnCharm);
    IExtendedCharmLearnableArbitrator learnableArbitrator =
            new DummyLearnableArbitrator(externalPrerequisiteId, internalPrerequisiteId, learCharmID);
    LearningCharmGroup internalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Melee.getId());
    LearningCharmGroup externalGroup = createSolarGroup(learnableArbitrator, charmTree, AbilityType.Archery.getId());
    container.setLearningCharmGroups(new ILearningCharmGroup[]{internalGroup, externalGroup});
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