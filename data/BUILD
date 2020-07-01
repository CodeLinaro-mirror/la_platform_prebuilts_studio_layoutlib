exports_files(srcs = ["framework_res.jar"])

# managed by go/iml_to_build
java_import(
    name = "layoutlib",
    jars = ["layoutlib.jar"],
    visibility = ["//visibility:public"],
)

# managed by go/iml_to_build
java_import(
    name = "layoutlib_native",
    jars = ["layoutlib_native.jar"],
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

# managed by go/iml_to_build [unb]
java_import(
    name = "unb.layoutlib_native",
    jars = ["layoutlib_native.jar"],
    tags = [
        "manual",
        "unb",
    ],
    visibility = ["//visibility:public"],
)

# managed by go/iml_to_build [unb]
java_import(
    name = "unb.layoutlib",
    jars = ["layoutlib.jar"],
    tags = [
        "manual",
        "unb",
    ],
    visibility = ["//visibility:public"],
)
