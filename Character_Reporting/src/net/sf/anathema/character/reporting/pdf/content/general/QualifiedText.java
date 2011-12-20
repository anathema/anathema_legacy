package net.sf.anathema.character.reporting.pdf.content.general;

public class QualifiedText {
  public final String text;
  public final TextType type;

  public QualifiedText(String text, TextType type) {
    this.text = text;
    this.type = type;
  }
}
