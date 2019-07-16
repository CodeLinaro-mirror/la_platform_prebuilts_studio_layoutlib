package(default_visibility = ["//visibility:public"])

# managed by go/iml_to_build
java_import(
    name = "data/layoutlib",
    jars = ["data/layoutlib.jar"],
    visibility = ["//visibility:public"],
)

# managed by go/iml_to_build
java_import(
    name = "data/layoutlib_native",
    jars = ["data/layoutlib_native.jar"],
    visibility = ["//visibility:public"],
)

filegroup(
    name = "data/res",
    srcs = glob(["data/res/**"]),
)

filegroup(
    name = "data/framework_res",
    srcs = ["data/framework_res.jar"],
)

filegroup(
    name = "data/fonts",
    srcs = glob(["data/fonts/**"]),
)

filegroup(
    name = "buildprop",
    srcs = ["build.prop"],
)

filegroup(
    name = "licenses",
    srcs = glob(["licenses/**"]),
)
