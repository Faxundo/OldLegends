package com.github.faxundo.old_legends.datagen.lang;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.custom.ReliquaryBlock;
import com.github.faxundo.old_legends.event.KeyInputHandler;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.FlutterEchoAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwake;
import com.github.faxundo.old_legends.util.OLHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class OLSpanishLanguageProvider extends FabricLanguageProvider {

    public OLSpanishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "es_ar", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(OLItem.ICON, "Old Legends");

        translationBuilder.add(OLItem.BOOK_OF_THE_LEGENDS, "Libro de las Leyendas");
        translationBuilder.add(OLItem.PALE_GEM, "Gema Pálida");
        translationBuilder.add(OLItem.END_EXTRACT, "Extracto del End");
        translationBuilder.add(OLItem.AWAKENING_UPGRADE, "Plantilla de Despertar");

        //Runes
        translationBuilder.add(OLItem.BLANK_RUNE, "Runa");
        translationBuilder.add(OLItem.DEATH_RUNE, "Runa de Muerte");
        translationBuilder.add(OLItem.SKY_RUNE, "Runa de Cielo");
        translationBuilder.add(OLItem.TIME_RUNE, "Runa de Tiempo");

        //Book of The Legends
        translationBuilder.add(BookOfTheLegends.PAGES, "Páginas");
        translationBuilder.add(BookOfTheLegends.OF, "de");
        translationBuilder.add(BookOfTheLegends.NO_OWNER, "No eres el dueño de este libro");
        translationBuilder.add(BookOfTheLegends.HAS_PAGE, "Ya posees esta página");
        translationBuilder.add(BookOfTheLegends.NEW_PAGE, "ha sido añadida al libro");
        translationBuilder.add(BookOfTheLegends.USAGE, "Para añadir páginas haz click izquierdo en el libro y luego click derecho en la página");

        //Emerald Mourning
        translationBuilder.add(OLItem.EMERALD_MOURNING, "Luto Esmeralda");
        translationBuilder.add(OLItem.EMERALD_MOURNING_AWAKE, "Luto Esmeralda Despierta");
        translationBuilder.add(EmeraldMourning.ABILITY_NAME_1, "Venganza Esmeralda");
        translationBuilder.add(EmeraldMourning.ABILITY_1, "Tiene +30% de daño vs Illagers");
        translationBuilder.add(EmeraldMourning.ABILITY_NAME_2, "Herencia de los Caídos");
        translationBuilder.add(EmeraldMourning.ABILITY_2, "Al derrotar a un Illager obtienes 1 Esmeralda");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE_1, "Tiene +60% de daño vs Illagers");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE_2, "Al derrotar a un Illager obtienes 1-3 Esmeralda");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_NAME_AWAKE, "Tiempo de Venganza");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE, "Invoca 3 Aldeanos para que cumplan con su venganza");

        //Swallows Storm
        translationBuilder.add(OLItem.SWALLOWS_STORM, "Tragatormentas");
        translationBuilder.add(OLItem.SWALLOWS_STORM_AWAKE, "Tragatormentas Despierta");
        translationBuilder.add(SwallowsStorm.CHARGES, "Cargas de Tormenta");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_1, "Energía de Tormenta");
        translationBuilder.add(SwallowsStorm.ABILITY_1, "Obtiene 1 Carga de Tormenta al bloquear un ataque y si bloquea un rayo, se cargará completamente. Durante una tormenta existe 15% posibilidad de ser objetivo de un rayo al bloquear");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_2, "Choque Refrescante");
        translationBuilder.add(SwallowsStorm.ABILITY_2, "Si tienes menos del 50% de Vida y tienes a Tragatormentas cargada, al bloquear, recuperarás 6 de Vida");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_3, "Electrificado");
        translationBuilder.add(SwallowsStorm.ABILITY_3, "Cuando bloqueas, el atacante recibe daño y un leve retroceso. Por cada 10 Cargas de Tormenta que poseas, causa +1 de Daño");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_1, "Obtiene 2 Cargas de Tormenta al bloquear un ataque y si bloquea un rayo, se cargará completamente. Durante una tormenta existe 30% posibilidad de ser objetivo de un rayo al bloquear");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_2, "Si tienes menos del 50% de Vida y tienes a Tragatormentas cargada, al bloquear, recuperarás 10 de Vida");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_3, "Cuando bloqueas, el atacante recibe daño y un leve retroceso. Por cada 10 Cargas de Tormenta que poseas, causa +2 de Daño");
        translationBuilder.add(SwallowsStormAwake.ABILITY_NAME_AWAKE, "Expulsión Explosiva");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE, "Causa una explosión eléctrica a tu alrededor que hace 15 de daño y un fuerte retroceso");

        //Flutter Echo
        translationBuilder.add(OLItem.FLUTTER_ECHO, "Eco de Aleteo");
        translationBuilder.add(OLItem.FLUTTER_ECHO_AWAKE, "Eco de Aleteo Despierta");
        translationBuilder.add(FlutterEcho.ABILITY_NAME_1, "Eco de Minería");
        translationBuilder.add(FlutterEcho.ABILITY_1, "Hay un 30% de generar un Eco de Mineral");
        translationBuilder.add(FlutterEcho.ABILITY_NAME_2, "Eco de Golpe");
        translationBuilder.add(FlutterEcho.ABILITY_2, "Hay un 30% de golpear de nuevo con un 50% del ataque base");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE_1, "Hay un 50% de generar un Eco de Mineral");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE_2, "Hay un 50% de golpear de nuevo con un 50% del ataque base");
        translationBuilder.add(FlutterEchoAwake.ABILITY_NAME_AWAKE, "Eco de Disparo");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE, "Lanza un eco que golpea entidades (14 de Daño) y rompe bloques");

        //Blocks
        translationBuilder.add(OLBlock.RUNE_TABLE, "Mesa de Runas");
        translationBuilder.add(OLBlock.ECHO_BLOCK, "Eco de Bloque");
        translationBuilder.add(OLBlock.ECHO_ORE, "Eco de Mineral");

        //Reliquary
        translationBuilder.add(OLBlock.RELIQUARY_BLOCK, "Relicario");
        translationBuilder.add(ReliquaryBlock.DESCRIPTION, "Solo puedes colocar objetos brillantes");
        translationBuilder.add(ReliquaryBlock.LOCK, "Cerrar");
        translationBuilder.add(ReliquaryBlock.LOCK_DESCRIPTION, "Espera a la noche para ver que sucede");
        translationBuilder.add(ReliquaryBlock.ALERT, "¡Este relicario está cerrado!");

        //Blueprints
        translationBuilder.add(OLItem.BLANK_BLUEPRINT, "Plano");
        translationBuilder.add(OLItem.RELIQUARY_BLUEPRINT, "Plano de Relicario");

        //Pages
        translationBuilder.add(OLItem.RELIQUARY_PAGE, "Página de Relicario");
        translationBuilder.add(OLItem.DEATH_RUNE_PAGE, "Página de Runa de Muerte");
        translationBuilder.add(OLItem.SKY_RUNE_PAGE, "Página de Runa de Cielo");
        translationBuilder.add(OLItem.TIME_RUNE_PAGE, "Página de Runa de Muerte");
        translationBuilder.add(OLItem.EMERALD_MOURNING_PAGE, "Página de Luto Esmeralda");
        translationBuilder.add(OLItem.SWALLOWS_STORM_PAGE, "Página de Tragatormentas");
        translationBuilder.add(OLItem.FLUTTER_ECHO_PAGE, "Página de Eco de Aleteo");

        //Tooltip
        translationBuilder.add(OLHelper.SHIFT_TOOLTIP, "▶ Shift");

        //Advancements
        translationBuilder.add(advancementName("icon"), "Old Legends");
        translationBuilder.add(advancementName("icon.description"), "Revela los misterios del pasado");
        translationBuilder.add(advancementName("emerald_mourning"), "Por una esmeralda");
        translationBuilder.add(advancementName("emerald_mourning.description"), "Obtén la Luto Esmeralda");
        translationBuilder.add(advancementName("emerald_mourning_awake"), "Esmeralda por esmeralda");
        translationBuilder.add(advancementName("emerald_mourning_awake.description"), "Usa una Plantilla de Despertar para despertar a Luto Esmeralda");
        translationBuilder.add(advancementName("swallows_storm"), "Hambre de Tormentas");
        translationBuilder.add(advancementName("swallows_storm.description"), "Obtén el Tragatormentas");
        translationBuilder.add(advancementName("swallows_storm_awake"), "Atragantado");
        translationBuilder.add(advancementName("swallows_storm_awake.description"), "Usa una Plantilla de Despertar para despertar a Tragatormentas");
        translationBuilder.add(advancementName("flutter_echo"), "Ecos");
        translationBuilder.add(advancementName("flutter_echo.description"), "Obtén el Eco de Aleteo");
        translationBuilder.add(advancementName("flutter_echo_awake"), "Eco Eco Eco");
        translationBuilder.add(advancementName("flutter_echo_awake.description"), "Usa una Plantilla de Despertar para despertar a Eco de Aleteo");
        translationBuilder.add(advancementName("rune"), "Old Power");
        translationBuilder.add(advancementName("rune.description"), "Fabrica una Runa");
        translationBuilder.add(advancementName("rune_table"), "Maestro de Runas");
        translationBuilder.add(advancementName("rune_table.description"), "Fabrica la Mesa de Runas");
        translationBuilder.add(advancementName("craft_all_runes"), "Coleccionista de Runas");
        translationBuilder.add(advancementName("craft_all_runes.description"), "Obten todas las runas");
        translationBuilder.add(advancementName("book_of_the_legends"), "Un Pasado Distante");
        translationBuilder.add(advancementName("book_of_the_legends.description"), "Consigue un Libro de Leyendas en una Cabaña de Bosque");
        translationBuilder.add(advancementName("owner_of_book"), "Dueño");
        translationBuilder.add(advancementName("owner_of_book.description"), "Sostén el libro y haz click derecho para convertirte en el dueño del libro");
        translationBuilder.add(advancementName("pages_of_book"), "Páginas");
        translationBuilder.add(advancementName("pages_of_book.description"), "Obtén una página. Coloca el libro sobre la página para añadirla");
        translationBuilder.add(advancementName("pale_gem"), "Gema sin Poder");
        translationBuilder.add(advancementName("pale_gem.description"), "Sé un arqueólogo y obtén una Gema Pálida");
        translationBuilder.add(advancementName("end_extract"), "Esencia del End");
        translationBuilder.add(advancementName("end_extract.description"), "Derrota a la dragona para obtener la esencia del End");
        translationBuilder.add(advancementName("awakening_upgrade"), "Despertar");
        translationBuilder.add(advancementName("awakening_upgrade.description"), "Fabrica la Plantilla de Despertar con el Extracto del End y la Gema Pálida");

        //Keys
        translationBuilder.add(KeyInputHandler.OL_KEY_CATEGORY, "Old Legends Key");
        translationBuilder.add(KeyInputHandler.MAIN_HAND_ABILITY, "Habilidad de Mano Principal");
        translationBuilder.add(KeyInputHandler.OFF_HAND_ABILITY, "Habilidad de Mano Secundaria");

        //Config
        translationBuilder.add("text.autoconfig.old_legends.title", "Old Legends");
        translationBuilder.add(configName("enableAwakening"), "Habilitar Despertar");
        translationBuilder.add(configTooltip("enableAwakening"), "Esto deshabilita la obtención de la Gema Pálida y el Extracto del End");
        translationBuilder.add(configName("paleGemDesertWellWeight"), "Probabilidad de Gema Pálida en Fuente del Desierto");
        translationBuilder.add(configName("paleGemDesertPyramidWeight"), "Probabilidad de Gema Pálida en Piramide");
        translationBuilder.add(configName("paleGemOceanRuinColdWeight"), "Probabilidad de Gema Pálida en Ruina de Océano Frío");
        translationBuilder.add(configName("paleGemOceanRuinWarmWeight"), "Probabilidad de Gema Pálida en Ruina de Océano Cálido");
        translationBuilder.add(configName("paleGemTrailsRuinsCommonWeight"), "Probabilidad de Gema Pálida en Ruina Perdida Común");
        translationBuilder.add(configName("paleGemTrailsRuinsRareWeight"), "Probabilidad de Gema Pálida en Ruina Perdida Rara");
        translationBuilder.add(configName("deathRuneSnifferDigging"), "Probabilidad de Runa de la Muerte cuando el Sniffer excava");
        translationBuilder.add(configName("skyRuneSnifferDigging"), "Probabilidad de Runa del Cielo cuando el Sniffer excava");

        translationBuilder.add(configName("emeraldMourning"), "Luto Esmeralda");
        translationBuilder.add(configName("emeraldMourning.enable"), "Habilitar Luto Esmeralda");
        translationBuilder.add(configName("emeraldMourning.weight"), "Peso en la LootTable");
        translationBuilder.add(configName("emeraldMourning.percentageIllager"), "% de Daño extra vs Illagers");
        translationBuilder.add(configName("emeraldMourning.percentageIllagerAwake"), "[Despierta] % de Daño extra vs Illagers");
        translationBuilder.add(configName("emeraldMourning.consumeDurability"), "[Despierta] % de Durabilidad consumida al usar la Habilidad Activa");
        translationBuilder.add(configName("emeraldMourning.mobTime"), "[Despierta] Tiempo de vida de las Criaturas de Luto");
        translationBuilder.add(configTooltip("emeraldMourning.mobTime"), "El tiempo está en Ticks. 20 Ticks = 1 segundo");
        translationBuilder.add(configName("emeraldMourning.cooldown"), "[Despierta]  Cooldown de la Habilidad Activa");
        translationBuilder.add(configTooltip("emeraldMourning.cooldown"), "El tiempo está en Ticks. 20 Ticks = 1 segundo");
        translationBuilder.add(configName("emeraldMourning.weightPage"), "[Página] Peso en la LootTable");

        translationBuilder.add(configName("swallowsStorm"), "Traga Tormentas");
        translationBuilder.add(configName("swallowsStorm.enable"), "Habilitar Traga Tormentas");
        translationBuilder.add(configName("swallowsStorm.weigth"), "Peso en la LootTable");
        translationBuilder.add(configName("swallowsStorm.maxCharges"), "Límite de Cargas de Tormenta");
        translationBuilder.add(configName("swallowsStorm.heal"), "Curación de Choque Refrescante");
        translationBuilder.add(configName("swallowsStorm.healLost"), "Vida para Choque Refrescante");
        translationBuilder.add(configTooltip("swallowsStorm.healLost"), "Cuanta vida necesitas perder para activar el efecto de Choque Refrescante");
        translationBuilder.add(configName("swallowsStorm.chance"), "% de impacto de Rayo de Energía de Tormenta");
        translationBuilder.add(configName("swallowsStorm.cooldown"), "Cooldown de Rayo");
        translationBuilder.add(configTooltip("swallowsStorm.stormCooldown"), "Cuanto tiempo debe pasar para que caiga otro rayo");
        translationBuilder.add(configName("swallowsStorm.consumeDurability"), "% Durabilidad consumida");
        translationBuilder.add(configTooltip("swallowsStorm.consumeDurability"), "Aplica a la Habilidad Activa [Despierta] y la Habilidad de Sanación");
        translationBuilder.add(configName("swallowsStorm.electrifiedDamage"), "Daño de Electrificado");
        translationBuilder.add(configTooltip("swallowsStorm.electrifiedDamage"), "Este número es dividido por 'Límite de Cargas de Tormenta´y el resultado es el daño causado");
        translationBuilder.add(configName("swallowsStorm.electrifiedStrength"), "Fuerza de Empuje de Electrificado");
        translationBuilder.add(configName("swallowsStorm.stormHealAwake"), "[Despierta] Curación de Electroshock Refrescante");
        translationBuilder.add(configName("swallowsStorm.maxChargesAwake"), "[Despierta] Límite de Cargas de Tormenta");
        translationBuilder.add(configName("swallowsStorm.electrifiedStrengthAwake"), "[Despierta] Fuerza de Empuje de Electrificado");
        translationBuilder.add(configName("swallowsStorm.explosiveDamage"), "[Despierta] Daño de Expulsión Explosiva");
        translationBuilder.add(configName("swallowsStorm.explosiveRange"), "[Despierta] Knockback Rango de Expulsión Explosiva");
        translationBuilder.add(configName("swallowsStorm.explosiveKnockback"), "[Despierta] Knockback Fuerza de Empuje de Expulsión Explosiva");
        translationBuilder.add(configName("swallowsStorm.explosiveCooldown"), "[Despierta] Cooldown de Expulsión Explosiva");
        translationBuilder.add(configName("swallowsStorm.weightPage"), "[Página] Peso en la LootTable");

        translationBuilder.add(configName("flutterEcho"), "Eco de Aleteo");
        translationBuilder.add(configName("flutterEcho.enable"), "Habilitar Eco de Aleteo");
        translationBuilder.add(configName("flutterEcho.weight"), "Peso en la LootTable");
        translationBuilder.add(configName("flutterEcho.mineChance"), "Probabilidad de Eco de Minería");
        translationBuilder.add(configName("flutterEcho.attackChance"), "Probabilidad de Eco de Golpe");
        translationBuilder.add(configName("flutterEcho.mineChanceAwake"), "[Despierta] Probabilidad de Eco de Minería");
        translationBuilder.add(configName("flutterEcho.attackChanceAwake"), "[Despierta] Probabilidad de Eco de Golpe");
        translationBuilder.add(configName("flutterEcho.cooldown"), "[Despierta] Cooldown de la Habilidad Activa");
        translationBuilder.add(configName("flutterEcho.consumeDurability"), "[Despierta] % Durabilidad consumida");
        translationBuilder.add(configTooltip("flutterEcho.consumeDurability"), "% de Durabilida consumida al usar la Habilidad Activa");
        translationBuilder.add(configName("flutterEcho.countLimit"), "[Despierta] Limite del Contador");
        translationBuilder.add(configTooltip("flutterEcho.countLimit"), "Limite de bloques y entidades que se pueden impactar");
        translationBuilder.add(configName("flutterEcho.weightPage"), "[Página] Peso en la LootTable");

        translationBuilder.add(configName("reliquary"), "Relicario");
        translationBuilder.add(configName("reliquary.grinningHoarderTime"), "Tiempo de activación");
        translationBuilder.add(configTooltip("reliquary.grinningHoarderTime"), "Tiempo en ticks que marca la hora en que se el Acaparador Risueño viene por el Relicario");
        translationBuilder.add(configName("reliquary.ancientCityWeight"), "Probabilidad de Plano en Ciudad Antigua");
        translationBuilder.add(configName("reliquary.strongholdWeight"), "Probabilidad de Plano en Fortaleza");
        translationBuilder.add(configName("reliquary.desertPyramidWeight"), "Probabilidad de Plano en Pirámide");
        translationBuilder.add(configName("reliquary.ruinsCommonWeight"), "Probabilidad de Plano en Ruina Común");
        translationBuilder.add(configName("reliquary.ruinsRareWeight"), "Probabilidad de Plano en Ruina Rara");
    }

    private String advancementName(String name) {
        return "advancement.old_legends." + name;
    }

    private String configName(String name) {
        return "text.autoconfig.old_legends.option." + name;
    }

    private String configTooltip(String name) {
        return "text.autoconfig.old_legends.option." + name + ".@Tooltip";
    }
}
