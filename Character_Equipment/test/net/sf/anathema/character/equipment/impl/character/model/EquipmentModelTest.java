package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.hero.abilities.model.SpecialtiesModelImpl;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.testing.BasicCharacterTestCase;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.DummyInitializationContext;
import net.sf.anathema.character.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.reporting.pdf.model.StatsModelImpl;
import net.sf.anathema.hero.abilities.model.AbilitiesModelImpl;
import net.sf.anathema.hero.attributes.model.AttributeModelImpl;
import net.sf.anathema.hero.equipment.EquipmentModelImpl;
import net.sf.anathema.hero.othertraits.model.pool.EssencePoolModelImpl;
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
    DummyInitializationContext context = new DummyInitializationContext();
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