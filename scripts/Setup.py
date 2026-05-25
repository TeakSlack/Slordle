import os
import sys
import platform
import subprocess

from SetupPython import SetupPython
SetupPython.Validate() # Ensure proper packages are installed before using them

from SetupPremake import SetupPremake

# Change directory to project root
os.chdir('../')

hasPremake = SetupPremake.Validate()

if not hasPremake:
    print("Premake not installed.")
    sys.exit(1)

print("Running premake...")

if platform.system() == "Windows":
    subprocess.call([os.path.abspath('./vendor/premake/bin/premake5.exe'), 'vs2026'])
elif platform.system() == "Linux":
    subprocess.call([os.path.abspath('./vendor/premake/bin/premake5'), 'gmake2'])