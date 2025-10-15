import org.ajoberstar.grgit.Grgit

plugins {
    id("net.kyori.blossom") version "2.2.0" apply false
}

logger.lifecycle("""
*******************************************
 You are building NuVotifier!
 If you encounter trouble:
 1) Try running 'build' in a separate Gradle run
 2) Use gradlew and not gradle
 3) If you still need help, you should reconsider building NuVotifier!

 Output files will be in [subproject]/build/libs
*******************************************
""")


applyRootArtifactoryConfig()

if (!project.hasProperty("gitCommitHash")) {
    apply(plugin = "org.ajoberstar.grgit")
    ext["gitCommitHash"] = try {
        Grgit.open(mapOf("currentDir" to project.rootDir))?.head()?.abbreviatedId
    } catch (e: Exception) {
        logger.warn("Error getting commit hash", e)

        "no.git.id"
    }
}