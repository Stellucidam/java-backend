package ch.heigvd.pro.b04.sessions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class ServerSessionCodeTest {

  @Test
  public void testFormatNotHexadecimal() {
    SessionCode code = SessionCode.builder().hexadecimal("wzx").build();
    assertFalse(SessionCode.conformsToFormat(code));
  }

  @Test
  public void testFormatHexadecimalButLowercase() {
    SessionCode code = SessionCode.builder().hexadecimal("0xabcd").build();
    assertFalse(SessionCode.conformsToFormat(code));
  }

  @Test
  public void testFormatHexadecimalButNotPrefixed() {
    SessionCode code = SessionCode.builder().hexadecimal("ABCD").build();
    assertFalse(SessionCode.conformsToFormat(code));
  }

  @Test
  public void testFormatHexadecimal() {
    SessionCode code = SessionCode.builder().hexadecimal("0xABCD").build();
    assertTrue(SessionCode.conformsToFormat(code));
  }

  @RepeatedTest(150)
  public void testGeneratedSessionCodeIsCorrectLength() {
    String code = ServerSession.createSessionCode();
    assertTrue(code.length() == 6);
  }
}
