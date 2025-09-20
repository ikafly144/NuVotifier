plugins {
    `java-library`
}

applyPlatformAndCoreConfiguration()
applyCommonArtifactoryConfig()

dependencies {
    "api"(project(":nuvotifier-api"))
    "implementation"("io.netty:netty-handler:${Versions.NETTYIO}")
    "implementation"("io.netty:netty-transport-native-epoll:${Versions.NETTYIO}:linux-x86_64")
    "implementation"("com.google.code.gson:gson:${Versions.GSON}")
    "testImplementation"("org.json:json:20250517") // retain this for testing reasons
    "testImplementation"("com.google.guava:guava:33.5.0-jre")
}