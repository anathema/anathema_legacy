package net.sf.anathema.development.character;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionWeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.module.CharacterCoreModule;
import net.sf.anathema.character.impl.module.CharacterModule;
import net.sf.anathema.character.intimacies.IntimaciesEncoder;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPartEncoder;
import net.sf.anathema.character.solar.reporting.PdfSolarVirtueFlawEncoder;
import net.sf.anathema.development.character.additional.DemoAlienArmour;
import net.sf.anathema.development.character.additional.DemoIntimacy;
import net.sf.anathema.development.character.additional.DemoMeleeWeapon;
import net.sf.anathema.development.character.additional.DemoNaturalArmour;
import net.sf.anathema.development.character.additional.DemoRangeWeapon;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.lib.control.BrowserControl;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCharacterSheetDemo {

  public static void main(String[] args) {
    Document document = new Document(PageSize.A4);
    try {
      System.err.println(document.getPageSize());
      File outputStream = new File("iText.pdf");
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputStream));
      document.open();
      PdfContentByte directContent = writer.getDirectContent();
      IGenericCharacter character = createDemoCharacter(CharacterType.SOLAR);
      DemoGenericDescription description = createDemoDescription();
      IResources resources = createDemoResources();
      PdfPageConfiguration pageConfiguration = PdfPageConfiguration.create(document.getPageSize());
      SecondEditionEncodingRegistry encodingRegistry = createEncodingRegistry(resources);
      SecondEditionPartEncoder partEncoder = new SecondEditionPartEncoder(
          encodingRegistry,
          resources,
          7,
          pageConfiguration);
      new PdfFirstPageEncoder(partEncoder, pageConfiguration).encode(directContent, character, description);
      BrowserControl.displayUrl(outputStream.toURL());
    }
    catch (Exception de) {
      de.printStackTrace();
    }
    finally {
      document.close();
    }
  }

  private static SecondEditionEncodingRegistry createEncodingRegistry(IResources resources) {
    SecondEditionEncodingRegistry encodingRegistry = new SecondEditionEncodingRegistry();
    encodingRegistry.setArmourContentEncoder(new SecondEditionArmourEncoder(resources, encodingRegistry.getBaseFont()));
    encodingRegistry.setWeaponContentEncoder(new SecondEditionWeaponryEncoder(resources, encodingRegistry.getBaseFont()));
    encodingRegistry.setIntimaciesEncoder(new IntimaciesEncoder(encodingRegistry.getBaseFont()));
    encodingRegistry.setGreatCurseEncoder(CharacterType.SOLAR, new PdfSolarVirtueFlawEncoder(
        resources,
        encodingRegistry.getBaseFont()));
    return encodingRegistry;
  }

  private static IResources createDemoResources() throws AnathemaException {
    Locale.setDefault(Locale.ENGLISH);
    IAnathemaResources resources = new AnathemaResources();
    new CharacterCoreModule().initAnathemaResources(resources);
    new CharacterModule().initAnathemaResources(resources);
    return resources;
  }

  private static IGenericCharacter createDemoCharacter(CharacterType characterType) {
    DemoGenericCharacter character = new DemoGenericCharacter(characterType);
    character.getConcept().setConceptText("Tolles Konzept");
    character.getConcept().setCasteType(AbyssalCaste.Day);
    character.getConcept().setWillpowerRegainingConceptName("Pedestrian Motivation");
    character.setRuleSet(ExaltedRuleSet.SecondEdition);
    character.addSpecialtyTrait(AbilityType.Archery);
    character.addSpecialtyTrait(AbilityType.Bureaucracy);
    character.addSubbedTrait(AbilityType.Craft);
    character.setPainTolerance(3);
    character.getEquipmentModel().addPrintArmour(new DemoNaturalArmour());
    character.getEquipmentModel().addPrintArmour(new DemoAlienArmour());
    character.getEquipmentModel().addPrintWeapon(new DemoRangeWeapon());
    character.getEquipmentModel().addPrintWeapon(new DemoMeleeWeapon());
    character.getIntimaciesModel().addEntry(new DemoIntimacy("Intimacy 1", 1, character));
    character.getIntimaciesModel().addEntry(new DemoIntimacy("Intimacy 2", 3, character));
    String longIntimacy = "Und ich bin jetzt eine ganz super lange Intimacy damit wir auch einen Umbruch bekommen";
    character.getIntimaciesModel().addEntry(new DemoIntimacy(longIntimacy, 0, character));
    return character;
  }

  private static DemoGenericDescription createDemoDescription() {
    DemoGenericDescription description = new DemoGenericDescription();
    description.setName("Hugo the Brave");
    return description;
  }
}