package nl.hannahsten.texifyidea.ui.symbols

import nl.hannahsten.texifyidea.lang.LatexCommand
import nl.hannahsten.texifyidea.lang.LatexMathCommand
import nl.hannahsten.texifyidea.lang.LatexRegularCommand
import nl.hannahsten.texifyidea.util.formatAsFileName

/**
 * Quickly creates a SymbolUiEntry from a command with the following consequences:
 *
 * - By default it generates only the command with slash, this can be changed by setting `generatedLatex`.
 * - The file path is "/nl/hannahsten/texifyidea/symbols/".
 * - The file name is either "math_ENUM-CONSTANT-LOWERCASE.png" for [LatexMathCommand], is
 *      "text_<ENUM-CONSTANT-LOWERCASE>.png" for [LatexRegularCommand], and
 *      "misc_<PACKAGE><FILENAME-SAFE-COMMAND>.png" for other commands. This can be changed by setting `customFileName`.
 * - The description is the command with a slash, can be changed by setting `customDescription`.
 * - Uses the command (in math mode if [LatexMathCommand]) to generate the image, can be changed by setting
 *      `customImageLatex`.
 *
 * @author Hannah Schellekens
 */
open class CommandUiEntry(
        command: LatexCommand,
        generatedLatex: String? = null,
        customFileName: String? = null,
        customDescription: String? = null,
        customImageLatex: String? = null
) : SymbolUiEntry {

    override val command: LatexCommand? = command

    override val generatedLatex: String = generatedLatex ?: command.commandDisplay

    private val fileName = customFileName ?: when (command) {
        is LatexMathCommand -> "math_${command.name.toLowerCase()}.png"
        is LatexRegularCommand -> "text_${command.name.toLowerCase()}.png"
        else -> "misc_${command.dependency.name}${command.command.formatAsFileName()}.png"
    }

    override val imagePath = "/nl/hannahsten/texifyidea/symbols/$fileName"

    override val imageLatex = customImageLatex ?: if (command is LatexMathCommand) {
        """
        \[
            ${command.display}        
        \]
        """
    }
    else command.display ?: "\\command.command"

    override val description = customDescription ?: when (command) {
        is LatexMathCommand -> command.name.toLowerCase().replace("_", " ") + " (math)"
        is LatexRegularCommand -> command.name.toLowerCase().replace("_", " ")
        else -> command.commandDisplay
    }
}