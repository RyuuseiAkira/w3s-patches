# Unlock avatar customization

- **Patch name:** `Unlock avatar customization`
- **Default:** `true` (enabled)
- **Dependency:** `pairipLicenseBypassPatch`
- **Source:** [`UnlockAvatarItemsPatch.kt`](../patches/src/main/kotlin/app/w3s/patches/w3schools/profile/UnlockAvatarItemsPatch.kt)

## What It Does

Removes client-side lock badges, claim requirements, and pre-save validation in the avatar customization tab (`Profile → Customize avatar`) so all avatar parts can be selected and previewed, and saving sends `PUT /pathfinder/league-api/user/profile` directly.

## How It Works

1. Locates the chunk containing `avatarSlotOrder` and `"Saving avatar:"` under `assets/public/_next/static/chunks/`.
2. Matches `AVATAR_CARD_STATES_REGEX` and forces every item card to `{...item, locked: false, canClaim: false}`, allowing selection without meeting client unlock checks.
3. Matches `AVATAR_SAVE_GUARD_REGEX` and removes the client-side pre-save validator block (`if(B&&w){...}`) that otherwise blocks `SAVE` with `"You can’t apply ... yet"` or `"Claim ... before saving your avatar."`.

## Scope & Limitations

- Client-side preview and save request emission are unlocked.
- Whether `PUT /pathfinder/league-api/user/profile` persists arbitrary asset IDs or validates claimed ownership on the backend depends on W3Schools' server enforcement.
