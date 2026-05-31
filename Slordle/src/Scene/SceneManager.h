#ifndef SCENE_MANAGER_H
#define SCENE_MANAGER_H

#include "Pch.h"
#include "IScene.h"

class SceneManager
{
public:
	void PushScene(std::unique_ptr<IScene> scene);
	void PopScene(IScene* scene);
	void Update(float deltaTime);
	void Render();

private:
	std::vector<std::unique_ptr<IScene>> m_Scenes;
};

#endif // SCENE_MANAGER_H