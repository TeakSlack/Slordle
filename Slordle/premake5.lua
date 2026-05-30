project "Slordle"
        kind "ConsoleApp"
        language "C++"
        cppdialect "C++20"
        targetdir ( "%{wks.location}/bin/" .. outputdir .. "/%{prj.name}" )
        objdir ( "%{wks.location}/bin-int/" .. outputdir .. "/%{prj.name}" )

        pchheader "Pch.h"
        pchsource "src/Pch.cpp"

        files
        {
            "src/**.h",
            "src/**.cpp",
        }

        includedirs 
        {
            "%{IncludeDir.spdlog}",
            "%{IncludeDir.raylib}"
        }

        libdirs 
        { 
            "%{LibraryDir.raylib}" 
        }

        filter "system:windows"
            links { "raylib", "winmm" }
            linkoptions { "/FORCE:MULTIPLE", "/ignore:4006" }
            defines { "_CRT_SECURE_NO_WARNINGS" }


        filter "system:linux"
            links { "raylib" }  -- Vulkan library for Linux (`libvulkan.so`)

        filter "configurations:Debug"
            defines { "DEBUG", "_DEBUG" }
            symbols "On"

        filter "configurations:Release"
            defines { "NDEBUG" }
            optimize "On"

        filter "action:gmake"
            buildoptions { "-Wall", "-Wextra", "-Werror", "-std=c++20" }

        filter "action:vs*"
            buildoptions { "/utf-8" }