load("//tools/adt/idea/studio:studio.bzl", "studio_data")
load("//tools/base/bazel:jvm_import.bzl", "jvm_import")
load("//tools/base/bazel:maven.bzl", "maven_library")
load("//tools/base/common:version.bzl", "BASE_VERSION")

exports_files(srcs = ["framework_res.jar"])

jvm_import(
    name = "layoutlib_import",
    jars = ["layoutlib.jar"],
)

maven_library(
    name = "layoutlib",
    srcs = [],
    bundled_deps = [
        ":layoutlib_import",
    ],
    coordinates = "com.android.tools.layoutlib:layoutlib:" + BASE_VERSION,
    description = "Layout library",
    jar_name = "layoutlib-mvn.jar",
    visibility = ["//visibility:public"],
)

filegroup(
    name = "layoutlib_extensions",
    srcs = [
        "layoutlib-extensions.jar",
    ],
    visibility = ["//visibility:public"],
)

studio_data(
    name = "data",
    files = [
        "framework_res.jar",
        "//prebuilts/studio/layoutlib/data:layoutlib_extensions",
        "//prebuilts/studio/layoutlib/data/fonts",
        "//prebuilts/studio/layoutlib/data/icu",
        "//prebuilts/studio/layoutlib/data/keyboards",
    ],
    files_linux = ["//prebuilts/studio/layoutlib/data/linux"],
    files_mac = ["//prebuilts/studio/layoutlib/data/mac"],
    files_mac_arm = ["//prebuilts/studio/layoutlib/data/mac-arm"],
    files_win = ["//prebuilts/studio/layoutlib/data/win"],
    mappings = {
        "prebuilts/studio/": "",
    },
    visibility = ["//visibility:public"],
)

filegroup(
    name = "native_libs",
    srcs = select({
        "//tools/base/bazel:windows": [
            "//prebuilts/studio/layoutlib/data/win",
        ],
        "//tools/base/bazel:darwin": [
            "//prebuilts/studio/layoutlib/data/mac",
        ],
        "//conditions:default": [
            "//prebuilts/studio/layoutlib/data/linux",
        ],
    }),
    visibility = ["//visibility:public"],
)

# managed by go/iml_to_build
filegroup(
    name = "layoutlib_files",
    srcs = ["layoutlib.jar"],
    visibility = ["//visibility:public"],
)
