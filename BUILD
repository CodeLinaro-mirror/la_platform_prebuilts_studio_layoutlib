package(default_visibility = ["//visibility:public"])

exports_files(srcs = ["build.prop"])

# can't have a BUILD file in licenses because AndroidStudioProperties adds the whole directory
filegroup(
    name = "licenses",
    srcs = glob(["licenses/*"]),
)
