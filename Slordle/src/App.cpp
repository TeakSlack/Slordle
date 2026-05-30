#include "Pch.h"
#include "App.h"

void App::Run()
{
	LOG_INFO("Initializing app...");
	Init();

	while (!m_Window.ShouldClose())
	{
		m_Input.Poll();
	}

	Shutdown();
}

void App::Init()
{
}

void App::Shutdown()
{

}