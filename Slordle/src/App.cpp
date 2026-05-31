#include "Pch.h"
#include "App.h"

void App::Run()
{
	LOG_INFO("Initializing app...");
	Init();

	while (!m_Window.ShouldClose())
	{
		float deltaTime = GetFrameTime();
		m_Input.Poll();

		m_SceneManager.Update(deltaTime);
		BeginDrawing();
		m_SceneManager.Render();
		EndDrawing();
	}

	LOG_INFO("Closing app...");
	Shutdown();
}

void App::Init()
{
}

void App::Shutdown()
{

}