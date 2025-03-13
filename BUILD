load("//tools/adt/idea/studio:studio.bzl", "studio_data")
load("//tools/base/bazel:gradle.bzl", "gradle_build")
load("//tools/base/build-system:hybrid_build.bzl", "GRADLE_PROPERTIES")

package(default_visibility = ["//visibility:public"])

exports_files(srcs = ["build.prop"])

# can't have a BUILD file in licenses because AndroidStudioProperties adds the whole directory
filegroup(
    name = "licenses",
    srcs = glob(["licenses/**"]),
)

studio_data(
    name = "layoutlib",
    files = [
        "build.prop",
        "//prebuilts/studio/layoutlib/data",
    ],
    mappings = {
        "prebuilts/studio/": "",
    },
    visibility = ["//visibility:public"],
)

filegroup(
    name = "runtime",
    srcs = [
        "build.prop",
        "//prebuilts/studio/layoutlib/data:native_libs",
        "//prebuilts/studio/layoutlib/data/fonts",
        "//prebuilts/studio/layoutlib/data/hyphen-data",
        "//prebuilts/studio/layoutlib/data/icu",
        "//prebuilts/studio/layoutlib/data/keyboards",
    ],
    visibility = ["//visibility:public"],
)

filegroup(
    name = "runtime-all-platforms",
    srcs = [
        "build.prop",
        "//prebuilts/studio/layoutlib/data/fonts",
        "//prebuilts/studio/layoutlib/data/hyphen-data",
        "//prebuilts/studio/layoutlib/data/icu",
        "//prebuilts/studio/layoutlib/data/keyboards",
        "//prebuilts/studio/layoutlib/data/linux",
        "//prebuilts/studio/layoutlib/data/mac",
        "//prebuilts/studio/layoutlib/data/mac-arm",
        "//prebuilts/studio/layoutlib/data/win",
    ],
)

gradle_build(
    name = "layoutlib-repo",
    build_file = "build.gradle.kts",
    data = [
        ":licenses",
        ":runtime-all-platforms",
        "//prebuilts/studio/layoutlib/data:framework_res.jar",
        "//prebuilts/studio/layoutlib/data:layoutlib_files",
    ],
    gradle_properties = GRADLE_PROPERTIES,
    output_file = "layoutlib-repository.zip",
    output_file_source = "repository.zip",
    tasks = ["zipRepo"],
)
