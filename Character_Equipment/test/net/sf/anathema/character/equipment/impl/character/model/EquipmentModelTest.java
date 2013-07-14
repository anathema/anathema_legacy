package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.hero.BasicCharacterTestCase;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.DummyHeroEnvironment;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.abilities.model.AbilitiesModelImpl;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributeModelImpl;
import net.sf.anathema.hero.equipment.EquipmentModelImpl;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModelImpl;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelImpl;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelImpl;
import net.sf.anathema.hero.traits.model.TraitModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class EquipmentModelTest {

  private EquipmentModelImpl model;

  @Before
  public void setUp() throws Exception {
    DummyHero hero = new BasicCharacterTestCase().createModelContextWithEssence2(new CreationTraitValueStrategy());
    hero.addModel(new EssencePoolModelImpl());
    hero.addModel(new SpecialtiesModelImpl());
    hero.addModel(new StatsModelImpl());
    hero.addModel(new TraitModelImpl());
    hero.addModel(new AbilitiesModelImpl());
    AttributeModel attributeModel = new AttributeModelImpl();
    DummyHeroEnvironment context = new DummyHeroEnvironment();
    attributeModel.initialize(context, hero);
    model = new EquipmentModelImpl();
    model.initialize(context, hero);
  }

  @Test
  public void removesStatsWithoutNpe() throws Exception {
    DummyEquipmentObject fromItem = new DummyEquipmentObject("from", "");
    IEquipmentStats stats = mock(IEquipmentStats.class);
    fromItem.addEquipment(stats);
    DummyEquipmentObject toItem = new DummyEquipmentObject("to", "");
    model.transferOptions(fromItem, toItem);
  }
}