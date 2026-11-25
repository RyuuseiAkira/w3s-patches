<h1 align="center">W3Schools Patches</h1>

<p align="center">
  Morphe patches for the <strong>W3Schools</strong> Android app (<code>com.w3schools.app.prod</code>).
</p>

<p align="center">
  <a href="https://github.com/RyuuseiAkira/w3s-patches/releases/latest"><img alt="Latest release" src="https://img.shields.io/github/v/release/RyuuseiAkira/w3s-patches?display_name=tag&label=Release&style=for-the-badge"></a>
  <a href="https://github.com/RyuuseiAkira/w3s-patches/releases"><img alt="Total downloads" src="https://img.shields.io/github/downloads/RyuuseiAkira/w3s-patches/total?label=Downloads&style=for-the-badge"></a>
  <a href="https://github.com/RyuuseiAkira/w3s-patches/blob/main/LICENSE"><img alt="License" src="https://img.shields.io/github/license/RyuuseiAkira/w3s-patches?style=for-the-badge"></a>
  <a href="https://github.com/RyuuseiAkira/w3s-patches"><img alt="GitHub stars" src="https://img.shields.io/github/stars/RyuuseiAkira/w3s-patches?style=for-the-badge"></a>
</p>

<p align="center">
  <a href="https://morphe.software/add-source?github=RyuuseiAkira/w3s-patches">
    <img src=".github/assets/add-to-morphe.svg" alt="Add to Morphe" height="48">
  </a>
</p>

---

## Overview

**W3Schools Patches** is a custom patch bundle built for [Morphe](https://morphe.software) targeting the W3Schools mobile app (`com.w3schools.app.prod`, tested on `v1.0.8`).

Every patch automatically applies an internal Pairip Play license check bypass so re-signed APKs launch cleanly when sideloaded without root.

> [!NOTE]
> **Scope & Server-Side Limits:** Client-gated mechanics (energy consumption and curriculum map locking) are unlocked by these patches. Server-enforced features such as Code Duel daily caps, Full Access entitlement checks, certification exam ownership, and backend League XP boosts remain controlled by W3Schools' backend and cannot be bypassed client-side.

## How to Use

1. **Add Source to Morphe:** Tap the **Add to Morphe** button above, or add this repository URL manually in **Morphe Manager** / **Morphe Desktop**:
   ```
   https://github.com/RyuuseiAkira/w3s-patches
   ```
2. **Install MicroG-RE (Required for Google Sign-In):**
   - Download and install [MicroG-RE](https://github.com/MorpheApp/MicroG-RE/releases) (`app.revanced.android.gms`).
   - Open MicroG-RE and sign in with your Google account before tapping **Continue with Google** inside the patched W3Schools app.
3. **Patch & Install:** Select the unpatched W3Schools APK / APKS bundle (`com.w3schools.app.prod`), choose the patches you want, patch, and install (uninstall any existing Play Store installation first if signatures differ).

## Included Patches

<details>
  <summary><code>Unlimited energy</code> — Bypasses client-side energy consumption</summary>

  Dynamically locates the `user-energy` module in the bundled Next.js chunks, replaces `getEnergy()` to report full `3/3` charges, and stubs `consumeEnergy()` to immediately succeed without calling `POST /competency-api/user-energy/decrement`.
</details>

<details>
  <summary><code>Unlock curriculum map</code> — Unlocks all lessons, checkpoints, and milestones</summary>

  Dynamically patches `resolveCurriculumAvailability` in the bundled Next.js chunks so `isLocked()` returns `false` and `isAccessible()` returns `true` across Home, Course Overview, and the Curriculum player.
</details>

<details>
  <summary><code>MicroG-RE support</code> — Enables Google Sign-In without root</summary>

  Redirects Google Play Services, C2DM, and AndroidX `CredentialManager` (`CredentialProviderFactory.tryCreatePostUProvider` -> `CredentialProviderPlayServicesImpl`) to `MicroG-RE` (`app.revanced.android.gms`), bypasses `GooglePlayServicesUtil` signature validation, adds `<queries>` visibility entries, and injects `SPOOFED_PACKAGE_NAME` / `SPOOFED_PACKAGE_SIGNATURE` metadata into `AndroidManifest.xml`.
</details>

## 🩹 Patches list

<!-- PATCHES_START -->
<!-- Automatically populated by release.yml when a release is published. -->
<!-- PATCHES_END -->

## Building Locally

Requires **JDK 21** and a GitHub Personal Access Token (`read:packages` scope) configured as `GITHUB_ACTOR` / `GITHUB_TOKEN` (or `gpr.user` / `gpr.key` in `~/.gradle/gradle.properties`) to pull Morphe packages from GitHub Packages:

```bash
./gradlew :patches:buildAndroid
```

The built `.mpp` bundle is generated at `patches/build/libs/patches-*.mpp`.

## Credits & License

- Built with [Morphe Patcher](https://github.com/MorpheApp/morphe-patcher) and [morphe-patches-template](https://github.com/MorpheApp/morphe-patches-template).
- Google Sign-In compatibility powered by [MicroG-RE](https://github.com/MorpheApp/MicroG-RE).
- Licensed under the [GNU General Public License v3.0](LICENSE) and [NOTICE](NOTICE).
