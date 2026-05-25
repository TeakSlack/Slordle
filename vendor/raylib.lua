project "raylib"
    kind "StaticLib"
    language "C"
    cdialect "C99"
    architecture "x86_64"
    targetdir ( "%{wks.location}/vendor/raylib/lib/" )
    objdir ( "%{wks.location}/bin-int/" .. outputdir .. "/%{prj.name}" )

    files
    {
        -- Headers
        "raylib/src/raylib.h",
        "raylib/src/rcamera.h",
        "raylib/src/rlgl.h",
        "raylib/src/raymath.h",
        "raylib/src/config.h",       -- compile-time feature flags
        "raylib/src/rgestures.h",    -- gesture detection (used internally by rcore)
        "raylib/src/rlconfig.h",     -- rlgl configuration

        -- Source
        "raylib/src/raudio.c",
        "raylib/src/rcore.c",
        "raylib/src/rmodels.c",
        "raylib/src/rshapes.c",
        "raylib/src/rtext.c",
        "raylib/src/rtextures.c",
        -- "raylib/src/utils.c",        -- logging, file I/O helpers used across all modules
    }

    -- rglfw.c compiles the bundled GLFW directly into the static lib.
    -- Excluded on web/Android where GLFW is not used.
    filter "system:windows or linux or macosx"
        files { "raylib/src/rglfw.c" }

    filter {}

    includedirs
    {
        "raylib/src",                          -- raylib.h, config.h, etc.
        "raylib/src/external",                   -- stb_*, cgltf, tinyobj, etc.
        "raylib/src/external/glfw/include",      -- GLFW headers needed by rglfw.c
    }

    defines { "PLATFORM_DESKTOP", "GRAPHICS_API_OPENGL_33" }

    -- Suppress deprecation warnings on macOS and tell clang to treat
    -- rglfw.c as Objective-C (required for the Cocoa backend)
    filter "system:macosx"
        defines      { "GL_SILENCE_DEPRECATION" }
        buildoptions { "-x objective-c" }

    filter "system:linux"
        defines { "_GNU_SOURCE" }   -- POSIX extensions needed by GLFW

    filter "system:windows"
        defines { "_CRT_SECURE_NO_WARNINGS" }

    filter "configurations:Debug"
        runtime  "Debug"
        symbols  "On"
        optimize "Off"

    filter "configurations:Release"
        runtime  "Release"
        symbols  "Off"
        optimize "Speed"
        defines  { "NDEBUG" }

    filter {}