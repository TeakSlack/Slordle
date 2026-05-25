# Slordle

> A simple Wordle clone built with C++ and Raylib.

This project only officially supports Windows and Linux at the moment.

## Installing

To setup the project environment, run the appropriate setup script for your platform.

* Windows
```bash
cd scripts
.\Setup-Win.bat
```

* **Linux:**
```bash
cd scripts
./Setup-Linux.sh
```

These scripts will install required dependencies and generate project files (Visual Studio solution on Windows or Makefile on Linux). For regenerating project files later, use:

- **Windows**: `GenProjects-Win.bat`
- **Linux**: `GenProjects-Linux.sh`

## Building

After setup, build the project as follows:
- **Windows**: Open the solution file in Visual Studio 2026 and build.
- **Linux**: Run `make` in the project directory.

## License

This project is licensed under the MIT License. See the LICENSE file for details.