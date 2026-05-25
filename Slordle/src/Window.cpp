#include "pch.h"
#include "Window.h"

Window::Window(int width, int height, const std::string& title)
{
	LOG_INFO("Creating window at {}x{}", width, height);
	SetTraceLogLevel(LOG_NONE);
	InitWindow(width, height, title.c_str());
}

Window::~Window()
{
	CloseWindow();
}

bool Window::ShouldClose() const
{
	return WindowShouldClose();
}

int Window::GetWidth() const
{
	return GetScreenWidth();
}

int Window::GetHeight() const
{
	return GetScreenHeight();
}