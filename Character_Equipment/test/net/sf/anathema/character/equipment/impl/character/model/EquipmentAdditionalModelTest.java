package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.natural.DefaultNaturalSoak;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.SpecialtiesCollection;
import net.sf.anathema.character.main.testing.dummy.DummyEssenceCharacterType;
import net.sf.anathema.character.main.testing.dummy.DummyGenericTrait;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.hero.othertraits.model.pool.EssencePoolModelImpl;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.traits.types.AttributeType.Stamina;
import static org.mockito.Mockito.mock;

public class EquipmentAdditionalModelTest {
  private final DummyEssenceCharacterType characterType = new DummyEssenceCharacterType();
  IArmourStats naturalArmor = new DefaultNaturalSoak(new DummyGenericTrait(Stamina, 5), characterType);
  IEquipmentTemplateProvider templateProvider = mock(IEquipmentTemplateProvider.class);
  SpecialtiesCollection context = mock(SpecialtiesCollection.class);
  IEquipmentCharacterDataProvider dataProvider = mock(IEquipmentCharacterDataProvider.class);
  EquipmentAdditionalModel model;

  @Before
  public void setUp() throws Exception {
    DummyHero hero = new DummyHero();
    hero.addModel(new EssencePoolModelImpl());
    model = new EquipmentAdditionalModel(hero, characterType, naturalArmor, templateProvider, context, dataProvider,
            new DummyMaterialRules());
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