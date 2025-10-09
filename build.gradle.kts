plugins {
  `java-library`
  `maven-publish`
}

version = "16.1.1"
group = "com.android.tools.layoutlib"

// Create task for a specific platform/architecture
fun registerNativeTask(nativeFolder: String) {
  tasks.register<Jar>(nativeFolder) {
    archiveClassifier.set(nativeFolder)
    from(layout.projectDirectory) {
      include("build.prop")
      include("data/$nativeFolder/**")
      include("data/icu/**")
      include("data/keyboards/**")
      include("data/fonts/**")
      include("data/hyphen-data/**")
      include("licenses/icu/**")
      if (nativeFolder.startsWith("mac")) {
        include("licenses/mac/**")
      } else {
        include("licenses/$nativeFolder/**")
      }
      exclude("**/BUILD")
    }
  }
}

fun createNativeConfig(architecture: String, os: String): NamedDomainObjectContainerCreatingDelegateProvider<Configuration> {
  return configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    attributes {
      attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, project.objects.named(MachineArchitecture::class.java, architecture))
      attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, project.objects.named(OperatingSystemFamily::class.java, os))
      attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
    }
  }
}

// Default task containing the native files for all platforms
tasks.jar {
  from(layout.projectDirectory) {
    include("build.prop")
    include("data/linux/**")
    include("data/mac/**")
    include("data/mac-arm/**")
    include("data/win/**")
    include("data/icu/**")
    include("data/keyboards/**")
    include("data/fonts/**")
    include("data/hyphen-data/**")
    include("licenses/**")
    exclude("licenses/layoutlib.jar.txt")
    exclude("**/BUILD")
  }
}

registerNativeTask("linux")
registerNativeTask("win")
registerNativeTask("mac")
registerNativeTask("mac-arm")

val linux: Configuration by createNativeConfig(MachineArchitecture.X86_64, OperatingSystemFamily.LINUX)
val windows: Configuration by createNativeConfig(MachineArchitecture.X86_64, OperatingSystemFamily.WINDOWS)
val macX86: Configuration by createNativeConfig(MachineArchitecture.X86_64, OperatingSystemFamily.MACOS)
@Suppress("UnstableApiUsage")
val macArm: Configuration by createNativeConfig(MachineArchitecture.ARM64, OperatingSystemFamily.MACOS)

artifacts.add("linux", tasks["linux"]) {}
artifacts.add("windows", tasks["win"]) {}
artifacts.add("macX86", tasks["mac"]) {}
artifacts.add("macArm", tasks["mac-arm"]) {}

val javaComponent = components.findByName("java") as AdhocComponentWithVariants
javaComponent.addVariantsFromConfiguration(linux) {}
javaComponent.addVariantsFromConfiguration(windows) {}
javaComponent.addVariantsFromConfiguration(macX86) {}
javaComponent.addVariantsFromConfiguration(macArm) {}

publishing {
  publications {
    create<MavenPublication>("layoutlib-runtime") {
      artifactId = "layoutlib-runtime"
      from(components["java"])
      pom {
        name.set("Layoutlib runtime")
        description.set("Native runtime for Layoutlib with its resources")
        url.set("https://developer.android.com/studio")
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            name.set("The Android Open Source Project")
          }
        }
        scm {
          connection.set("scm:git:https://android.googlesource.com/platform/frameworks/base/")
          url.set("https://cs.android.com/android/platform/frameworks/base/")
        }
      }
    }
    create<MavenPublication>("layoutlib") {
      artifactId = "layoutlib"
      artifact(file(layout.projectDirectory.file("data/layoutlib.jar")))
      artifact(file(layout.projectDirectory.file("licenses/layoutlib.jar.txt")))
      pom {
        name.set("Layoutlib")
        description.set("Rendering library for Android resources on non-Android platforms")
        url.set("https://developer.android.com/studio")
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            name.set("The Android Open Source Project")
          }
        }
        scm {
          connection.set("scm:git:https://android.googlesource.com/platform/frameworks/layoutlib/")
          url.set("https://cs.android.com/android/platform/frameworks/layoutlib/")
        }
      }
    }
    create<MavenPublication>("layoutlib-resources") {
      artifactId = "layoutlib-resources"
      artifact(file(layout.projectDirectory.file("data/framework_res.jar")))
      pom {
        name.set("Layoutlib resources")
        description.set("Android resource files used by Layoutlib")
        url.set("https://developer.android.com/studio")
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            name.set("The Android Open Source Project")
          }
        }
        scm {
          connection.set("scm:git:https://android.googlesource.com/platform/frameworks/base/")
          url.set("https://cs.android.com/android/platform/frameworks/base/")
        }
      }
    }
  }
  repositories {
    maven {
      url = uri("${layout.buildDirectory.asFile.get()}/publishing-repository")
    }
  }
}

val androidHostOut = file(System.getenv("BUILD_DIR") ?: "$rootDir/build")

// Task that creates a ZIP file the repository to publish on GMaven
tasks.register<Zip>("zipRepo") {
  from("${layout.buildDirectory.asFile.get()}/publishing-repository")
  destinationDirectory = androidHostOut
  archiveFileName = "repository.zip"
  dependsOn("publish")
}
