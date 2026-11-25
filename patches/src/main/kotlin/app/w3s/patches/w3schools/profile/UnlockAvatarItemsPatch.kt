// patches/src/main/kotlin/app/w3s/patches/w3schools/profile/UnlockAvatarItemsPatch.kt
package app.w3s.patches.w3schools.profile

import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.patch.listApkEntries
import app.morphe.patcher.patch.patch
import app.morphe.patcher.patch.resourceFile
import app.w3s.patches.w3schools.misc.pairip.pairipLicenseBypassPatch
import app.w3s.patches.w3schools.shared.Constants.COMPATIBILITY_W3SCHOOLS

private const val CHUNKS_PREFIX = "assets/public/_next/static/chunks/"
private const val IDENT = """[A-Za-z_${'$'}][\w${'$'}]*"""

private val AVATAR_CARD_STATES_REGEX = Regex(
    """if\(!($IDENT)\)\{let $IDENT=!$IDENT&&!$IDENT;return\{\.\.\.($IDENT),locked:$IDENT,canClaim:!1,\.\.\.$IDENT\?\{requirementLabel:$IDENT\}:\{\}\}\}""" +
        """let $IDENT=\1\.claimed\.has\($IDENT\)\|\|$IDENT,$IDENT=\1\.unlocked\.has\($IDENT\),$IDENT=!$IDENT&&!$IDENT,$IDENT=!$IDENT&&$IDENT&&!$IDENT&&!$IDENT;""" +
        """return\{\.\.\.\2,locked:$IDENT,canClaim:$IDENT,\.\.\.$IDENT\?\{requirementLabel:$IDENT\}:\{\}\}""",
)

private val AVATAR_SAVE_GUARD_REGEX = Regex(
    """if\($IDENT&&$IDENT\)\{let $IDENT=function\($IDENT,$IDENT,$IDENT,$IDENT,$IDENT\)\{.*?Claim.*?finish the unlock requirements first\..*?return null\}\([^)]+\);if\($IDENT\)return void $IDENT\($IDENT\)\}""",
)

@Suppress("unused")
val unlockAvatarItemsPatch = patch(
    name = "Unlock avatar customization",
    description = "Unlocks locked avatar customization cards and bypasses client-side save validation (server validation may still apply when saving).",
    default = true,
) {
    compatibleWith(COMPATIBILITY_W3SCHOOLS)

    dependsOn(pairipLicenseBypassPatch)

    execute {
        val chunkEntries = listApkEntries(CHUNKS_PREFIX).filter { it.endsWith(".js") }
        var patchedCount = 0

        for (entry in chunkEntries) {
            val file = resourceFile(entry)
            var content = file.readText()
            if (!content.contains("avatarSlotOrder") || !content.contains("Saving avatar:")) continue

            val cardsMatch = AVATAR_CARD_STATES_REGEX.find(content)
                ?: throw PatchException("Found avatar customization chunk $entry, but card lock logic did not match.")
            content = content.replaceRange(cardsMatch.range, "return{...${cardsMatch.groupValues[2]},locked:!1,canClaim:!1}")

            val saveMatch = AVATAR_SAVE_GUARD_REGEX.find(content)
                ?: throw PatchException("Found avatar customization chunk $entry, but save validation logic did not match.")
            content = content.replaceRange(saveMatch.range, "")

            file.writeText(content)
            patchedCount++
        }

        if (patchedCount == 0) {
            throw PatchException("Could not find avatar customization module in $CHUNKS_PREFIX.")
        }
    }
}
