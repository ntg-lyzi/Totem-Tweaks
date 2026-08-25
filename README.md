# Totem Tweaks

**Description:** Helps to reduce the Totem Size
**Author:** Lyzi
**Loader:** Fabric — Minecraft 1.21.11+ (built against 1.21.11, works on 1.21.9+)

## What it does

When you open your survival inventory, if you have **exactly one** Totem of
Undying somewhere in your hotbar and your hand isn't already on it, your
selected hotbar slot automatically jumps to it — so it's instantly ready to
grab. If you have zero or more than one totem in your hotbar, it does nothing
(so it never guesses wrong).

No keybind — it's controlled entirely from a small settings screen.

## Usage

Type **`/totemtweaks`** in chat to open the settings screen, where you can
toggle the feature on/off. Your choice is saved to
`config/totemtweaks/config.json` and remembered between sessions.

## Compiling (GitHub method — no PC needed)

1. Create a new repo on GitHub (e.g. `totem-tweaks`) and upload every file in
   this project, keeping the folder structure.
2. Push to `main` — GitHub Actions will build automatically
   (`.github/workflows/build.yml` is already included).
3. Repo → **Actions** tab → latest **"Build Mod"** run → **Artifacts** →
   download `totem-tweaks-jar`.
4. Unzip it — inside is `totem-tweaks-<version>.jar`.
5. Put that jar in `.minecraft/mods/`, alongside Fabric API + Fabric Loader
   for 1.21.11.

## Compiling locally

```
gradlew build      (Windows: gradlew.bat build)
```
Output: `build/libs/totem-tweaks-1.0.0.jar`

Double check `gradle.properties` versions against
[fabricmc.net/develop](https://fabricmc.net/develop) before building if it's
been a while — Minecraft/Fabric versions move fast.
