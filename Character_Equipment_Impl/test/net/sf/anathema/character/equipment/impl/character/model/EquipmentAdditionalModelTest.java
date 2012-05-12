package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.dummy.DummyEquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import org.junit.Test;

import static net.sf.anathema.character.generic.traits.types.AttributeType.Stamina;
import static net.sf.anathema.character.generic.type.CharacterType.SPIRIT;
import static org.mockito.Mockito.mock;

public class EquipmentAdditionalModelTest {
  IArmourStats naturalArmor = new NaturalSoak(new DummyGenericTrait(Stamina, 5), SPIRIT);
  IEquipmentTemplateProvider templateProvider = mock(IEquipmentTemplateProvider.class);
  IGenericSpecialtyContext context = mock(IGenericSpecialtyContext.class);
  IEquipmentCharacterDataProvider dataProvider = mock(IEquipmentCharacterDataProvider.class);
  EquipmentAdditionalModel model = new EquipmentAdditionalModel(SPIRIT, naturalArmor, templateProvider, context,
          dataProvider);

  @Test
  public void removesStatsWithoutNpe() throws Exception {
    DummyEquipmentObject fromItem = new DummyEquipmentObject("from", "");
    IEquipmentStats stats = mock(IEquipmentStats.class);
    fromItem.addEquipment(stats);
    DummyEquipmentObject toItem = new DummyEquipmentObject("to", "");
    model.transferOptions(fromItem, toItem);
  }
}