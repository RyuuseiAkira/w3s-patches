# 🧩 W3Schools Patches

Morphe patches for **W3Schools** (`com.w3schools.app.prod`).

## ➕ Add to Morphe

**One tap** (open on the phone that runs Morphe Manager):
[**Add to Morphe**](https://morphe.software/add-source?github=RyuuseiAkira/w3s-patches)

**Manually:** Morphe Manager → patch sources **+** → **Remote** → paste
`https://github.com/RyuuseiAkira/w3s-patches`

Uninstall the Play Store version of W3Schools first, as the patched build is signed with a different key.
For **Continue with Google**, install [**MicroG-RE**](https://github.com/MorpheApp/MicroG-RE/releases) (`app.revanced.android.gms`) and sign in to your Google account inside MicroG-RE before logging into the patched app.

> GPLv3 · Not affiliated with W3Schools. Targets the W3Schools versions in the patch list below.
> All three patches automatically apply an internal Pairip license check bypass so re-signed sideloaded builds launch cleanly. Server-enforced features (Code Duel quota, Full Access entitlements, certification exams, and backend League XP boost) remain controlled by W3Schools' backend.

## 🩹 Patches

<!-- PATCHES_START EXPANDED -->
> **[v1.0.0-dev.1](https://github.com/RyuuseiAkira/w3s-patches/releases/tag/v1.0.0-dev.1)**&nbsp;&nbsp;&middot;&nbsp;&nbsp;`dev`&nbsp;&nbsp;&middot;&nbsp;&nbsp;**3 patches** across **1 app**&nbsp;&nbsp;&middot;&nbsp;&nbsp;[Full details](PATCHES.md)

| # | App | Patches | Version | Package |
|---|---|---|---|---|
| 1 | [**W3Schools**](PATCHES.md#w3schools-comw3schoolsappprod) | 3 | `1.0.8` | [`com.w3schools.app.prod`](https://play.google.com/store/apps/details?id=com.w3schools.app.prod) |
<!-- PATCHES_END -->

## 🛠️ Building locally

- `./gradlew buildAndroid` → the bundle lands in `patches/build/libs/patches-*.mpp`
- Patch that file with [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop) like any other bundle.

## 📜 License

[GNU General Public License v3.0](LICENSE).

## 🙏 Credits

- Built using [MorpheApp/morphe-patches-template](https://github.com/MorpheApp/morphe-patches-template) and [MorpheApp/morphe-patcher](https://github.com/MorpheApp/morphe-patcher) (GPLv3).
- **MicroG-RE support**: adapted from [MorpheApp/morphe-patches](https://github.com/MorpheApp/morphe-patches) GmsCore support (GPLv3) for [MorpheApp/MicroG-RE](https://github.com/MorpheApp/MicroG-RE).
