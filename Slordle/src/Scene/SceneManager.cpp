#include "Pch.h"
#include "SceneManager.h"

void SceneManager::PushScene(std::unique_ptr<IScene> scene)
{
	m_Scenes.emplace_back(std::move(scene));
}

void SceneManager::PopScene(IScene* scene)
{
	auto it = std::find_if(m_Scenes.begin(), m_Scenes.end(),
		[scene](const std::unique_ptr<IScene>& s) { return s.get() == scene; });

	if (it != m_Scenes.end()) m_Scenes.erase(it);
}

void SceneManager::Update(float deltaTime)
{
	for (auto& scene : m_Scenes)
		scene->Update(deltaTime);
}

void SceneManager::Render()
{
	BeginDrawing();
	for (auto& scene : m_Scenes)
		scene->Render();
	EndDrawing();
}