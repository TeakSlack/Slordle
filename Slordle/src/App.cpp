#include "Pch.h"
#include "App.h"
#include "Scene/MenuScene.h"

void App::Run()
{
	LOG_INFO("Initializing app...");
	Init();

	while (!m_Window.ShouldClose())
	{
		float deltaTime = GetFrameTime();
		m_Input.Poll();

		m_SceneManager.Update(deltaTime);
		m_SceneManager.Render();
	}

	LOG_INFO("Closing app...");
	Shutdown();
}

void App::Init()
{
	m_MenuScene = std::make_unique<MenuScene>();
	m_SceneManager.PushScene(std::move(m_MenuScene));
}

void App::Shutdown()
{

}