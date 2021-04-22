exports_files(srcs = ["framework_res.jar"])

load("//tools/adt/idea/studio:studio.bzl", "studio_data")

# managed by go/iml_to_build
java_import(
    name = "layoutlib-legacy",
    jars = ["layoutlib_legacy.jar"],
    visibility = ["//visibility:public"],
)

studio_data(
    name = "data",
    files = [
        "framework_res.jar",
        "layoutlib-extensions.jar",
        "//prebuilts/studio/layoutlib/data/fonts",
        "//prebuilts/studio/layoutlib/data/icu",
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
