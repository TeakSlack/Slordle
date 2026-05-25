include "Dependencies.lua"

workspace "Slordle"
    configurations {"Debug", "Release"}
    platforms {"x86_64"}
    startproject "Slordle"

    outputdir = "%{cfg.buildcfg}-%{cfg.system}-%{cfg.architecture}"

group "Projects"
    include "Slordle"
    include "vendor/raylib"