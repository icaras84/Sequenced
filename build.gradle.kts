plugins {
    id("java")
    id("maven-publish")
}

group = "com.icaras84"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("Sequenced") {
            groupId = "com.icaras84"
            artifactId = "sequenced"
            version = "0.0.1"

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "Sequenced"
            url = uri(layout.buildDirectory.dir("sequenced"))
        }
    }
}