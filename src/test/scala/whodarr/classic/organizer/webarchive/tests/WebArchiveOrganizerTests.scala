package whodarr.classic.organizer.webarchive.tests

import org.scalatest.flatspec.AnyFlatSpec
import whodarr.classic.organizer.{SerialFilenameConverter, SerialFilenameSanitizer}
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter

class WebArchiveOrganizerTests extends AnyFlatSpec {
  "WebArchive Sanitizer" should "remove .autogenerated from file names" in {
    // Arrange
    // Note: For the time being, these parameters aren't necessary for the sanitizing functionality.
    val sanitizer: SerialFilenameSanitizer = WebArchiveSerialFilenameConverter("S08E03", 10, 57)

    // Act / Assert
    assert(sanitizer.sanitizeFilename(
      "Doctor Who - S08E03 (057) - The Claws of Axos (2).autogenerated.vtt") ===
      "Doctor Who - S08E03 (057) - The Claws of Axos (2).vtt")
    assert(sanitizer.sanitizeFilename(
      "Doctor Who - S08E03 (057) - The Claws of Axos (3).autogenerated.txt") ===
      "Doctor Who - S08E03 (057) - The Claws of Axos (3).txt")
  }

  "WebArchive Converter" should "convert episode names properly" in {
    // Arrange
    val converter: SerialFilenameConverter = WebArchiveSerialFilenameConverter("S08E03", 10, 57)

    // Act / Assert
    assert(converter.convertEpisodeName(
      "Doctor Who - S08E03 (057) - The Claws of Axos (2).autogenerated.vtt") ===
      "Doctor Who - S08E12 (057) - The Claws of Axos (2).vtt")
    assert(converter.convertEpisodeName(
      "Doctor Who - S08E03 (057) - The Claws of Axos (3).autogenerated.txt") ===
      "Doctor Who - S08E13 (057) - The Claws of Axos (3).txt")
  }
}