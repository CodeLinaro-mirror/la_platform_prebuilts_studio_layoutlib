load("//tools/adt/idea/studio:studio.bzl", "studio_data")

package(default_visibility = ["//visibility:public"])

exports_files(srcs = ["build.prop"])

# can't have a BUILD file in licenses because AndroidStudioProperties adds the whole directory
filegroup(
    name = "licenses",
    srcs = glob(["licenses/*"]),
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
