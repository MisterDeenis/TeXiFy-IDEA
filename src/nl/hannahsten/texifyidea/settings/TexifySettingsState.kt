package nl.hannahsten.texifyidea.settings

import nl.hannahsten.texifyidea.lang.commands.LatexCommand
import nl.hannahsten.texifyidea.lang.commands.LatexGenericRegularCommand
import nl.hannahsten.texifyidea.run.linuxpdfviewer.InternalPdfViewer

data class TexifySettingsState(
    var automaticSecondInlineMathSymbol: Boolean = true,
    var automaticUpDownBracket: Boolean = true,
    var automaticItemInItemize: Boolean = true,
    var automaticDependencyCheck: Boolean = true,
    var autoCompile: Boolean = false,
    var autoCompileOnSaveOnly: Boolean = false,
    var continuousPreview: Boolean = false,
    var includeBackslashInSelection: Boolean = false,
    var showPackagesInStructureView: Boolean = false,
    var enableTextidote: Boolean = false,
    var textidoteOptions: String = "--check en --output singleline --no-color",
    var automaticQuoteReplacement: TexifySettings.QuoteReplacement = TexifySettings.QuoteReplacement.NONE,
    var missingLabelMinimumLevel: LatexCommand = LatexGenericRegularCommand.SUBSECTION,
    var pdfViewer: InternalPdfViewer = InternalPdfViewer.firstAvailable()
)
