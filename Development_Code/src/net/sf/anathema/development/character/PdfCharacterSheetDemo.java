package net.sf.anathema.development.character;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.module.CharacterCoreModule;
import net.sf.anathema.character.impl.module.CharacterModule;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPartEncoder;
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
      PdfPageConfiguration pageConfiguration = PdfPageConfiguration.create(document.getPageSize());
      SecondEditionPartEncoder partEncoder = new SecondEditionPartEncoder(createDemoResources(), 7, pageConfiguration);
      new PdfFirstPageEncoder(partEncoder, pageConfiguration).encode(directContent, character, description);
      BrowserControl.displayUrl(outputStream.toURL());
    }
    catch (Exception de) {
      System.err.println(de.getMessage());
    }
    finally {
      document.close();
    }
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
    character.addPrintArmour(new DemoNaturalArmour());
    character.addPrintArmour(new DemoAlienArmour());
    character.addPrintWeapon(new DemoRangeWeapon());
    character.addPrintWeapon(new DemoMeleeWeapon());
    return character;
  }

  private static DemoGenericDescription createDemoDescription() {
    DemoGenericDescription description = new DemoGenericDescription();
    description.setName("Hugo the Brave");
    return description;
  }
}